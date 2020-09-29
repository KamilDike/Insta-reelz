package com.tul.instagram.service;

import com.tul.instagram.model.Medias;
import com.tul.instagram.model.Video;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProfileService {
    ReelService reelService;

    public ProfileService(ReelService reelService) {
        this.reelService = reelService;
    }

    private static String queryHash = "15bf78a4ad24e33cbd838fdb31353ac1";

    /**
     * Loading data from user.
     * @param name userName.
     * @return List of images.
     */
    @Async
    public Medias getProfile(String name) {
        String URL = "https://www.instagram.com/" + name + "/?__a=1";
        String JSON = JSONFetcher.readUrl(URL);
        String userID = JSONFetcher.getID();
        String nextPage = JSONFetcher.getEndCursor("first");
        String avatarUrl = JSONFetcher.getProfilePic();
        String channelName = JSONFetcher.getChannelName();
        List<String> photoUrls = JSONFetcher.getImages(JSON);
        List<Video> videos = JSONFetcher.getVideosJsonPath();
        System.out.println("Got profile");
        return new Medias(photoUrls, videos, userID, nextPage, avatarUrl, channelName);
    }

    public Medias getProfile(String ID, String pageCursor) {
        String URL = "https://www.instagram.com/graphql/query/?query_hash=" + queryHash +
                "&variables=%7B%22id%22%3A%22" + ID + "%22%2C%22first%22%3A12%2C%22after%22%3A%22" + pageCursor + "%3D%3D%22%7D";
        String JSON = JSONFetcher.readUrl(URL);
        String nextPage = JSONFetcher.getEndCursor("next");
        List<String> photoUrls = JSONFetcher.getImages(JSON);
        List<Video> videos = JSONFetcher.getVideosJsonPath();
        System.out.println("Got profile");
        return new Medias(photoUrls, videos, ID, nextPage, "", "");
    }

    public Medias getProfileAll(String name) {
        System.out.println("1st Page");

        Medias mediasOut = new Medias();
        List<String> photoUrls = new LinkedList<>();
        List<Video> videos = new LinkedList<>();
        CompletableFuture<Medias> completableFuture1 = CompletableFuture.completedFuture(getProfile(name));

        try {
            completableFuture1.thenAccept(medias -> {
                        mediasOut.setUserID(medias.getUserID());
                        mediasOut.setAvatarUrl(medias.getAvatarUrl());
                        mediasOut.setChannelName(medias.getChannelName());
                        mediasOut.setNextPage(medias.getNextPage());
                        photoUrls.addAll(medias.getPhotoUrls());
                        videos.addAll(medias.getVideos());
                    }
            );
            System.out.println("Completed");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Instant start = Instant.now();

        while (!mediasOut.getNextPage().equals("null") &&
                Duration.between(start, Instant.now()).getSeconds() < 60){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("NEXTPG");

            CompletableFuture<Medias> completableFuture = CompletableFuture
                    .completedFuture(getProfile(mediasOut.getUserID(), mediasOut.getNextPage()));
            
                try {
                    completableFuture.thenAccept(medias -> {
                                mediasOut.setNextPage(medias.getNextPage());
                                photoUrls.addAll(medias.getPhotoUrls());
                                videos.addAll(medias.getVideos());
//                                try {
//                                    Thread.currentThread().wait(2000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
                            }
                    );
                    System.out.println("Complted");
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
        mediasOut.setPhotoUrls(photoUrls);
        mediasOut.setVideos(videos);

        return mediasOut;
    }
}

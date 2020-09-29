package com.tul.instagram.model;

import lombok.*;

import java.util.List;

@Data
public class Medias {
    private List<String> photoUrls;
    private List<Video> videos;
    private String userID;
    private String nextPage;
    private String avatarUrl;
    private String channelName;

    public Medias(List<String> photoUrls, List<Video> videos, String userID, String nextPage,
                  String avatarUrl, String channelName) {
        this.photoUrls = photoUrls;
        this.videos = videos;
        this.userID = userID;
        this.nextPage = nextPage;
        this.avatarUrl = avatarUrl;
        this.channelName = channelName;
    }

    public Medias() {
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getUserID() {
        return userID;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}

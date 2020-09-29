package com.tul.instagram.model;

public class Video {
    private String videoUrl;
    private String title;

    public Video(String videoUrl, String title) {
        this.videoUrl = videoUrl;
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoUrl='" + videoUrl + '\'' +
                ", message='" + title + '\'' +
                '}';
    }
}

package com.tul.instagram.model;

public class Reel {
    String avatarSrc;
    String channel;
    String url;
    String title;

    public Reel() { }

    public Reel(String avatarSrc, String channel, String url, String title) {
        this.avatarSrc = avatarSrc;
        this.channel = channel;
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public String getMessage() {
        return title;
    }

    public void setMessage(String message) {
        this.title = message;
    }
}

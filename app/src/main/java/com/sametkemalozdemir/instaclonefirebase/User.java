package com.sametkemalozdemir.instaclonefirebase;

public class User {
    private String email;
    private String downloadedUrl;
    private String  commentUser;

    public User(String email, String downloadedUrl, String commentUser) {
        this.email = email;
        this.downloadedUrl = downloadedUrl;
        this.commentUser = commentUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDownloadedUrl() {
        return downloadedUrl;
    }

    public void setDownloadedUrl(String downloadedUrl) {
        this.downloadedUrl = downloadedUrl;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }



}

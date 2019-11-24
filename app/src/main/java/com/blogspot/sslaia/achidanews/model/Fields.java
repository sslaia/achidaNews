package com.blogspot.sslaia.achidanews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("byline")
    @Expose
    private String author;

    private String shortUrl;

    @SerializedName("thumbnail")
    @Expose
    private String image;

    public Fields(String author, String shortUrl, String image) {
        this.author = author;
        this.shortUrl = shortUrl;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

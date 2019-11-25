package com.blogspot.sslaia.achidanews.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class Article {

    private String author;
    private String title;
    private String url;

    @SerializedName("urlToImage")
    private String image;

    @SerializedName("publishedAt")
    private String pubDate;

    private String description;

    public static DiffUtil.ItemCallback<Article> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.title == newItem.title;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Article article = (Article) obj;
        return article.title == this.title;
    }

    public Article(String author, String title, String url, String image, String pubDate, String description) {
        this.author = author;
        this.title = title;
        this.url = url;
        this.image = image;
        this.pubDate = pubDate;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate= pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

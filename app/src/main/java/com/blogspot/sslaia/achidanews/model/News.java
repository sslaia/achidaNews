package com.blogspot.sslaia.achidanews.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class News {

    private String id;

    @SerializedName("sectionName")
    private String section;

    @SerializedName("webPublicationDate")
    private String pubDate;

    @SerializedName("webTitle")
    private String title;

    @SerializedName("webUrl")
    private String url;

    private String apiUrl;
    private Fields fields;

    public News(String id, String section, String pubDate, String title, String url, String apiUrl, Fields fields) {
        this.id = id;
        this.section = section;
        this.pubDate = pubDate;
        this.title = title;
        this.url = url;
        this.apiUrl = apiUrl;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return section;
    }

    public void setSectionName(String section) {
        this.section = section;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public static DiffUtil.ItemCallback<News> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<News>() {
        @Override
        public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.url == newItem.url;
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        News news = (News) obj;
        return news.url== this.url;
    }

}

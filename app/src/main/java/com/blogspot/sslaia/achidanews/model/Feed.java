package com.blogspot.sslaia.achidanews.model;

import java.util.List;

public class Feed {

    private String status;
    private long totalResults;
    private List<Article> articles;

    public Feed(String status, long totalResults, List<Article> news) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = news;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getNews() {
        return articles;
    }

    public void setNews(List<Article> news) {
        this.articles = news;
    }
}

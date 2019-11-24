package com.blogspot.sslaia.achidanews.model;

import java.util.List;

public class Response {

    private int pageSize;
    private int currentPage;
    private int pages;
    private String orderBy;
    private List<News> results = null;

    public Response(int pageSize, int currentPage, int pages, String orderBy, List<News> news) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pages = pages;
        this.orderBy = orderBy;
        this.results = news;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<News> getNews() {
        return results;
    }

    public void setNews(List<News> news) {
        this.results = news;
    }
}

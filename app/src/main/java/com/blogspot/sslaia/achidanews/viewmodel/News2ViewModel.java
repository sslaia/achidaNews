package com.blogspot.sslaia.achidanews.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.blogspot.sslaia.achidanews.datasource.News2DataSourceFactory;
import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.model.Article;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class News2ViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<Article>> newsLiveData;
    private String SOURCES, COUNTRY, CATEGORY, API_KEY;

    private Controller controller;

    public News2ViewModel(@NonNull Controller controller, String sources, String country, String category, String apiKey) {
        this.controller = controller;
        SOURCES = sources;
        COUNTRY = country;
        CATEGORY = category;
        API_KEY = apiKey;
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        News2DataSourceFactory factory = new News2DataSourceFactory(controller, SOURCES, COUNTRY, CATEGORY, API_KEY);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        newsLiveData = (new LivePagedListBuilder(factory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Article>> getNewsLiveData() {
        return newsLiveData;
    }
}

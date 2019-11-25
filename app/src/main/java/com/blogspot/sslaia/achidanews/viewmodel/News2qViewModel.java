package com.blogspot.sslaia.achidanews.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.blogspot.sslaia.achidanews.datasource.News2qDataSourceFactory;
import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.model.Article;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class News2qViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<Article>> newsLiveData;
    private String QUERY, SOURCES, SORT_BY, API_KEY;

    private Controller controller;

    public News2qViewModel(@NonNull Controller controller, String query, String sources, String sortBy, String apiKey) {
        this.controller = controller;
        QUERY = query;
        SOURCES = sources;
        SORT_BY = sortBy;
        API_KEY = apiKey;
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        News2qDataSourceFactory factory = new News2qDataSourceFactory(controller, QUERY, SOURCES, SORT_BY, API_KEY);

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

package com.blogspot.sslaia.achidanews.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.blogspot.sslaia.achidanews.datasource.NewsDataSourceFactory;
import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.model.News;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<News>> newsLiveData;
    private String QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY;

    private Controller controller;

    public NewsViewModel(@NonNull Controller controller, String query,
                         String section, String order_by, String show_fields, String api_key) {
        this.controller = controller;
        this.QUERY = query;
        this.SECTION = section;
        this.ORDER_BY = order_by;
        this.SHOW_FIELDS = show_fields;
        this.API_KEY = api_key;
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        NewsDataSourceFactory newsDataSourceFactory = new NewsDataSourceFactory(
                controller, QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        newsLiveData = (new LivePagedListBuilder(newsDataSourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<News>> getNewsLiveData() {
        return newsLiveData;
    }

}

package com.blogspot.sslaia.achidanews.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.blogspot.sslaia.achidanews.helpers.Controller;


public class News2qDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<News2qDataSource> mutableLiveData;
    private News2qDataSource dataSource;
    private Controller controller;
    private String QUERY, SOURCES, SORT_BY, API_KEY;

    public News2qDataSourceFactory(Controller controller, String query, String sources, String sortBy, String apiKey) {
        this.controller = controller;
        QUERY = query;
        SOURCES = sources;
        SORT_BY = sortBy;
        API_KEY = apiKey;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        dataSource = new News2qDataSource(controller, QUERY, SOURCES, SORT_BY, API_KEY);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<News2qDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

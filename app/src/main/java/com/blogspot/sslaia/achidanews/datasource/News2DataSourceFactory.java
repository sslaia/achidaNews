package com.blogspot.sslaia.achidanews.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.blogspot.sslaia.achidanews.helpers.Controller;

public class News2DataSourceFactory extends DataSource.Factory {

    private MutableLiveData<News2DataSource> mutableLiveData;
    private News2DataSource dataSource;
    private Controller controller;
    private String SOURCES, COUNTRY, CATEGORY, API_KEY;

    public News2DataSourceFactory(Controller controller, String sources, String country, String category, String api_key) {
        this.controller = controller;
        SOURCES = sources;
        COUNTRY = country;
        CATEGORY = category;
        API_KEY = api_key;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        dataSource = new News2DataSource(controller, SOURCES, COUNTRY, CATEGORY, API_KEY);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<News2DataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

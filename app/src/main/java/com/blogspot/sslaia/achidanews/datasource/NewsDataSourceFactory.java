package com.blogspot.sslaia.achidanews.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.blogspot.sslaia.achidanews.helpers.Controller;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<NewsDataSource> mutableLiveData;
    private NewsDataSource dataSource;
    private Controller controller;
    private String QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY;

    public NewsDataSourceFactory(Controller controller, String query, String section,
                                 String order_by, String show_fields, String gu_api_key) {

        this.controller = controller;
        this.mutableLiveData = new MutableLiveData<>();
        this.QUERY = query;
        this.SECTION = section;
        this.ORDER_BY = order_by;
        this.SHOW_FIELDS = show_fields;
        this.API_KEY = gu_api_key;
    }

    @Override
    public DataSource create() {
        dataSource = new NewsDataSource(controller, QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

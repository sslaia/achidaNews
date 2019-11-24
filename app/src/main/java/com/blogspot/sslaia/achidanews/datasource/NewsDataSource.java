package com.blogspot.sslaia.achidanews.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.model.Item;
import com.blogspot.sslaia.achidanews.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDataSource extends PageKeyedDataSource<Long, News> {

    private Controller controller;
    private String QUERY, SECTION, ORDER_BY, SHOW_FIELDS, API_KEY;

    public NewsDataSource(Controller controller, String query, String section,
                          String order_by, String show_fields, String api_key) {
        this.controller = controller;
        this.QUERY = query;
        this.SECTION = section;
        this.ORDER_BY = order_by;
        this.SHOW_FIELDS = show_fields;
        this.API_KEY = api_key;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull final LoadInitialCallback<Long, News> callback) {

        controller.getNewsApi().getNews(QUERY, SECTION, ORDER_BY, SHOW_FIELDS, 1l, params.requestedLoadSize, API_KEY)
                .enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(Call<Item> call, Response<Item> response) {
                        if (response.isSuccessful()) {
                            callback.onResult(response.body().getNewsResponse().getNews(), null, 2l);
                        }
                    }

                    @Override
                    public void onFailure(Call<Item> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, News> callback) {

        controller.getNewsApi().getNews(QUERY, SECTION, ORDER_BY, SHOW_FIELDS, params.key, params.requestedLoadSize, API_KEY)
                .enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()) {
                    long nextKey = (params.key == response.body().getNewsResponse().getPages()) ? null : params.key + 1;
                    callback.onResult(response.body().getNewsResponse().getNews(), nextKey);
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

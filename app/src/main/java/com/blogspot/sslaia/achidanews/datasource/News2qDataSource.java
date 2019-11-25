package com.blogspot.sslaia.achidanews.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.blogspot.sslaia.achidanews.helpers.Controller;
import com.blogspot.sslaia.achidanews.model.Article;
import com.blogspot.sslaia.achidanews.model.Feed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class News2qDataSource extends PageKeyedDataSource<Long, Article> {

    private Controller controller;
    private String QUERY, SOURCES, SORT_BY, API_KEY;

    public News2qDataSource(Controller controller, String query, String sources, String sortBy, String apiKey) {
        this.controller = controller;
        QUERY = query;
        SOURCES = sources;
        SORT_BY = sortBy;
        API_KEY = apiKey;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull final LoadInitialCallback<Long, Article> callback) {

        controller.getNews2Api().getNews2q(QUERY, SOURCES, SORT_BY, API_KEY, 1l, params.requestedLoadSize)
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        if (response.isSuccessful()) {
                            callback.onResult(response.body().getNews(), null, 2l);
                        }
                    }

                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Article> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, Article> callback) {

        controller.getNews2Api().getNews2q(QUERY, SOURCES, SORT_BY, API_KEY, params.key, params.requestedLoadSize)
                .enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    long nextKey = (params.key == response.body().getTotalResults()) ? null : params.key + 1;
                    callback.onResult(response.body().getNews(), nextKey);
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

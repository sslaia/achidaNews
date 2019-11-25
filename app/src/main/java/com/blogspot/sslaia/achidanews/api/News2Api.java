package com.blogspot.sslaia.achidanews.api;

import com.blogspot.sslaia.achidanews.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface News2Api {

    @GET("top-headlines")
    Call<Feed> getNews2(@Query("sources") String sources,
                        @Query("country") String country,
                        @Query("category") String category,
                        @Query("apiKey") String apiKey,
                        @Query("page") long page,
                        @Query("pageSize") int pageSize);

    @GET("everything")
    Call<Feed> getNews2q(@Query("q") String query,
                         @Query("sources") String sources,
                         @Query("sortBy") String sortBy,
                         @Query("apiKey") String apiKey,
                         @Query("page") long page,
                         @Query("pageSize") int pageSize);
}

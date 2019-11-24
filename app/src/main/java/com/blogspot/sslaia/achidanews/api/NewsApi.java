package com.blogspot.sslaia.achidanews.api;

import com.blogspot.sslaia.achidanews.model.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("search")
    Call<Item> getNews(@Query("q") String query,
                       @Query("section") String section,
                       @Query("order-by") String orderBy,
                       @Query("show-fields") String showFields,
                       @Query("page") long page,
                       @Query("page-size") int pageSize,
                       @Query("api-key") String apiKey);
}

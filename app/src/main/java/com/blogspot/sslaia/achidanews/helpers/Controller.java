package com.blogspot.sslaia.achidanews.helpers;

import android.app.Application;
import android.content.Context;

import com.blogspot.sslaia.achidanews.api.NewsApi;
import com.blogspot.sslaia.achidanews.api.NewsApiFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class Controller extends Application {

    private NewsApi newsApi;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private static Controller get(Context context) {
        return (Controller) context.getApplicationContext();
    }

    public static Controller create(Context context) {
        return Controller.get(context);
    }

    public NewsApi getNewsApi() {
        if(newsApi == null) {
            newsApi = NewsApiFactory.create();
        }
        return newsApi;
    }

    public void setNewsApi(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }
}

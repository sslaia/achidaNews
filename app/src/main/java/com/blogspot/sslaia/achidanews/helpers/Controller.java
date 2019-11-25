package com.blogspot.sslaia.achidanews.helpers;

import android.app.Application;
import android.content.Context;

import com.blogspot.sslaia.achidanews.api.News2Api;
import com.blogspot.sslaia.achidanews.api.News2ApiFactory;
import com.blogspot.sslaia.achidanews.api.NewsApi;
import com.blogspot.sslaia.achidanews.api.NewsApiFactory;

public class Controller extends Application {

    private NewsApi newsApi;
    private News2Api news2Api;

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

    public News2Api getNews2Api() {
        if(news2Api == null) {
            news2Api = News2ApiFactory.create();
        }
        return news2Api;
    }
}

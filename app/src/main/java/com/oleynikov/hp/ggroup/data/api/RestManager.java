package com.oleynikov.hp.ggroup.data.api;

import com.oleynikov.hp.ggroup.BuildConfig;
import com.oleynikov.hp.ggroup.REST.RetrofitApi;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Vladyslav on 08.01.2018
 */

public class RestManager {
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 10;
    private final OkHttpClient.Builder httpClientBuilder;

    public RestManager() {
        httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG)
            httpClientBuilder.addInterceptor(logging);
    }

    // card api link unknown yet
    public RetrofitApi provideCardApi(String link) {
        return new Retrofit.Builder()
                .baseUrl(link)
                .client(httpClientBuilder.build())
                .addConverterFactory(SimpleXmlConverterFactory.create(new Persister(new AnnotationStrategy())))
                .build()
                .create(RetrofitApi.class);
    }

    public RetrofitApi provideRetrofitApi(String link) {
        return new Retrofit.Builder()
                .baseUrl("http://g-group.com.ua/")
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class);
    }
}

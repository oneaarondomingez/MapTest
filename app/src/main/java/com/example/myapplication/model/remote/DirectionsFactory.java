package com.example.myapplication.model.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 3/15/17.
 */

public class DirectionsFactory {
    public static Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(DirectionsService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}

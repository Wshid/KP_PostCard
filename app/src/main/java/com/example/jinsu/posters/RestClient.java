package com.example.jinsu.posters;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient<T> {
    private T service;
    private String baseUrl = "https://192.168.0.5:3000";

    public T getClient(Class<? extends T> type)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(type);
        return service;
    }
}

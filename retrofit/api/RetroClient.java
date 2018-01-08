package com.adeeb.internify.retrofit.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

  //Base URL
    private static final String ROOT_URL = "https://shopicruit.myshopify.com/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Getting API service
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}

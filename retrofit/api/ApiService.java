package com.adeeb.internify.retrofit.api;

import com.adeeb.internify.model.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //Retrofit
    @GET("admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<ProductList> getMyJSON();
}

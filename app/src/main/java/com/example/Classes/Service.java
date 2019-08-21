package com.example.Classes;

import com.example.Classes.API.JsonCouponsApi;
import com.example.Classes.API.JsonGetBarcodeByCouponApi;
import com.example.Classes.API.JsonPlaceApi;
import com.example.Classes.API.JsonQRCodeApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static final Service ourInstance = new Service();
    private static final String BASE_URL = "https://eco-urn.ru";
    private Retrofit mRetrofit;

    public static Service getInstance() {
        return ourInstance;
    }

    private Service() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public JsonPlaceApi getJSONAPlaceApi() {
        return mRetrofit.create(JsonPlaceApi.class);
    }
    public JsonCouponsApi getJSONCouponsApi(){
        return mRetrofit.create(JsonCouponsApi.class); }
    public JsonQRCodeApi getJsonQRCodeApi(){
        return mRetrofit.create(JsonQRCodeApi.class); }
    public JsonGetBarcodeByCouponApi getJSONGetBarcodeByCouponApi(){
        return mRetrofit.create(JsonGetBarcodeByCouponApi.class); }

}


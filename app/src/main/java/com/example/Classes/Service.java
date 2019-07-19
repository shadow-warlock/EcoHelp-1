package com.example.Classes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static final Service ourInstance = new Service();
    private static final String BASE_URL = "http://95.214.63.199";
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
    public JsonBarcodeApi getJSONBarCodeApi(){
        return mRetrofit.create(JsonBarcodeApi.class); }
    public JsonQRCodeApi getJsonQRCodeApi(){
        return mRetrofit.create(JsonQRCodeApi.class); }
    public JsonGetBarcodeByCouponApi JSONGetBarcodeByCouponApi(){
        return mRetrofit.create(JsonGetBarcodeByCouponApi.class); }

}


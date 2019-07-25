package com.example.Classes.API;

import com.example.Classes.Pojo.BarCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonGetBarcodeByCouponApi {
    @GET("/api/barcode/get/by_coupon/{id}")
    Call<BarCode> getBarcodeWithId(@Path("id")int id);
}


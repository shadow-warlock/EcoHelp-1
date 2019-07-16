package com.example.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonBarcodeApi {
    @GET("/api/coupons/get/")
    Call<List<BarCode>> loadList();
}
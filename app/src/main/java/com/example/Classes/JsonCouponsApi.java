package com.example.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonCouponsApi {
    @GET("/api/coupons/get/")
    Call<List<Coupons>> loadList();
}
package com.example.Classes.API;

import com.example.Classes.Pojo.Pandomats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceApi {
    @GET("/api/device/get/")
    Call<List<Pandomats>> loadList();
}
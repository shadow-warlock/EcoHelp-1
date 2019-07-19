package com.example.Classes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceApi {
    @GET("/api/device/get/")
    Call<List<Pandomats>> loadList();
}
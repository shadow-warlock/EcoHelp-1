package com.example.Classes.API;

import com.example.Classes.Pojo.QRCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonQRCodeApi {
    @GET("/api/qrcode/get/")
    Call<List<QRCode>> loadList();
}
package com.example.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pandomats {
    @SerializedName("id")
    @Expose
    private String address;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("lastDeviceData")
    @Expose
    private LastDeviceData lastDeviceData;
    @SerializedName("image")
    @Expose
    private Object image;



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public LastDeviceData getLastDeviceData() {
        return lastDeviceData;
    }

    public void setLastDeviceData(LastDeviceData lastDeviceData) {
        this.lastDeviceData = lastDeviceData;
    }
    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

}
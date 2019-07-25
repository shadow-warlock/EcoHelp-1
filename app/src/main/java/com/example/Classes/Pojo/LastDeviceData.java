package com.example.Classes.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastDeviceData {

    @SerializedName("occupancy")
    @Expose
    private Occupancy occupancy;

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Occupancy occupancy) {
        this.occupancy = occupancy;
    }

}
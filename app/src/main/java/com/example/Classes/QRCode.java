package com.example.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QRCode {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
package com.example.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Coupons implements Parcelable {

    private int logo;
    private String TAG;


    public Coupons(int logo,String TAG) {

        this.logo = logo;
        this.TAG = TAG;
    }
    public String getTAG(){return this.TAG;}
    public void setString(String TAG){this.TAG = TAG;}
    public int getLogo(){
        return this.logo;
    }
    public void setLogo(int logo){
        this.logo = logo;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("logo", logo);


        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

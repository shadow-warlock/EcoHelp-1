package com.example.Classes.ExpandingRecycler;

import android.os.Parcel;
import android.os.Parcelable;

public class InfoCoupons implements Parcelable {
    private String info;
    private String date;
    Long id;
    Long cost;
    String end;


    public InfoCoupons(String info,String date,Long id,Long cost,String end) {
        this.info = info;
        this.date = date;
        this.id = id;
        this.cost = cost;
        this.end = end;
    }
    protected InfoCoupons(Parcel in){
        info = in.readString();
        date = in.readString();
    }

    public static final Creator<InfoCoupons> CREATOR = new Creator<InfoCoupons>() {
        @Override
        public InfoCoupons createFromParcel(Parcel in) {
            return new InfoCoupons(in);
        }

        @Override
        public InfoCoupons[] newArray(int size) {
            return new InfoCoupons[size];
        }
    };

    public String getInfo(){
        return info;
    }
    public void setInfo (String info){
        this.info = info;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getEnd(){
        return end;
    }
    public Long getId(){
        return id;
    }
    public Long getCost(){
        return cost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(info);
    }
}

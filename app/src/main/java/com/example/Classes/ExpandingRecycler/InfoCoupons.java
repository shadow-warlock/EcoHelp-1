package com.example.Classes.ExpandingRecycler;

import android.os.Parcel;
import android.os.Parcelable;

public class InfoCoupons implements Parcelable {
    private String info;

    public InfoCoupons(String info) {
        this.info = info;
    }
    protected InfoCoupons(Parcel in){
        info = in.readString();
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(info);
    }
}

package com.example.Classes.ExpandingRecycler;

import android.os.Parcel;
import android.os.Parcelable;

public class infoCoupons implements Parcelable {
    private String info;

    public infoCoupons(String info) {
        this.info = info;
    }
    protected infoCoupons(Parcel in){
        info = in.readString();
    }

    public static final Creator<infoCoupons> CREATOR = new Creator<infoCoupons>() {
        @Override
        public infoCoupons createFromParcel(Parcel in) {
            return new infoCoupons(in);
        }

        @Override
        public infoCoupons[] newArray(int size) {
            return new infoCoupons[size];
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

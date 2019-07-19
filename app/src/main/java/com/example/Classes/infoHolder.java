package com.example.Classes;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class infoHolder extends ChildViewHolder {
    private TextView couponsName;
    public infoHolder(View itemView) {
        super(itemView);

    }
    public void onBind(infoCoupons infoCoupons){
        couponsName.setText(infoCoupons.getInfo());
    }
    public void setInfo(String info){
        couponsName.setText(info);
    }

}

package com.example.Classes.ExpandingRecycler;

import android.view.View;
import android.widget.TextView;

import com.example.ecohelp.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class infoHolder extends ChildViewHolder {
    private TextView couponsName;
    private TextView dateView;
    public infoHolder(View itemView) {
        super(itemView);
        couponsName = itemView.findViewById(R.id.textView8);
        dateView = itemView.findViewById(R.id.textView9);


    }
    public void onBind(InfoCoupons infoCoupons){
        couponsName.setText(infoCoupons.getInfo());
        dateView.setText(infoCoupons.getDate());
    }
    public void setInfo(String info){
        couponsName.setText(info);
    }

    public void setDate(String date){
        dateView.setText(date);
    }
}

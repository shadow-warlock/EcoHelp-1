package com.example.ecohelp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class Library extends Activity {


List<Coupons> couponss = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerAdapter adapter = new recyclerAdapter(this,couponss);
        recyclerView.setAdapter(adapter);

    }
    private void setInitialData(){

        couponss.add(new Coupons("Лента","-500",R.drawable.aaaaaaaaaaa));
    }

}


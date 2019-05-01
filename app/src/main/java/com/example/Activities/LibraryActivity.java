package com.example.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Classes.Coupons;
import com.example.ecohelp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends Activity {


List<Coupons> couponss = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        RecyclerAdapter adapter = new RecyclerAdapter(this,couponss);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Сoupons");
        FirebaseRecyclerOptions<Coupons> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Coupons>()
                .setQuery(query, Coupons.class)
                .build();

    }
    private void setInitialData(){

        couponss.add(new Coupons("Лента","-500",R.drawable.aaaaaaaaaaa));
    }

}


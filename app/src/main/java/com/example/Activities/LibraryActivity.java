package com.example.Activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.Classes.Coupons;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;



public class LibraryActivity extends BaseActivity{
    private static final String TAG = "LibraryActivity";
    private RecyclerView recyclerView;


    List<Coupons> couponss = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Activity = "Library";
        isOnline(LibraryActivity.this);
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Drawer(toolbar,LibraryActivity.this,LibraryActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Купоны");
        }



        setInitialData();

        recyclerView = findViewById(R.id.list);

        RecyclerAdapter adapter = new RecyclerAdapter(this, couponss);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }




    public void  setInitialData(){

    }

}


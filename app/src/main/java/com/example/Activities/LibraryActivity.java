package com.example.Activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.ecohelp.R;


import java.util.ArrayList;
import java.util.List;



public class LibraryActivity extends BaseActivity{
    private static final String TAG = "LibraryActivity";
    private RecyclerView recyclerView;

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




    }




    public void  setInitialData(){

    }

}




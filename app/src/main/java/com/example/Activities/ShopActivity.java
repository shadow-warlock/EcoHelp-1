package com.example.Activities;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;


import com.example.ecohelp.R;


public class ShopActivity extends BaseActivity  {
    AlertDialog.Builder adp100;
    AlertDialog.Builder adp300;
    AlertDialog.Builder adp500;
    AlertDialog.Builder adl100;
    AlertDialog.Builder adl300;
    AlertDialog.Builder adl500;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Activity = "Shop";
        context = ShopActivity.this;
        String title = "Подтверждение";
        String message = "Вы уверены";
        String button1String = "Да ";
        String button2String = "Нет";
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        isOnline(ShopActivity.this);
        Drawer(toolbar,ShopActivity.this,ShopActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Магазины");
        }



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }




}
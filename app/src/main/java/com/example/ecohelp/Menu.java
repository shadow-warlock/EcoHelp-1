package com.example.ecohelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;




public class Menu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClickMaps(View view) {
        Intent intent = new Intent(Menu.this, MapsActivity.class);
        startActivity(intent);
    }
}
package com.example.ecohelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class Menu extends Activity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_menu);

    }

    public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.map) {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            } else if (i == R.id.signOut) {
                // Как реализовать выход из аккаунта?




            }

        }
    }



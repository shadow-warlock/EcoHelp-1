package com.example.ecohelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Menu extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_menu);

    }

    public void onClick1(View v) {
            int i = v.getId();
            if (i == R.id.map) {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            } else if (i == R.id.signOut) {
              FirebaseAuth.getInstance().signOut();
              Intent intent = new Intent(this, MainActivity.class);
              startActivity(intent);
            }
            else if (i==R.id.QR){
              Intent intent = new Intent(this, QR.class);
              startActivity(intent);



            }

        }
    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }
    }



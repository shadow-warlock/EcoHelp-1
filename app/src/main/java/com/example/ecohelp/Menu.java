package com.example.ecohelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class Menu extends Activity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    private void signOut() {

        mAuth.signOut();


        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> startmainactivity() );
    }

    public void startmainactivity() {

        Intent intent = new Intent(Menu.this, Main2Activity.class);
        startActivity(intent);
        finish();

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
              finish();
            }
            else if (i==R.id.QR){
              Intent intent = new Intent(this, QR.class);
              startActivity(intent);



            }
            else if (i==R.id.menu){
                Intent intent = new Intent(this,Library.class);
                startActivity(intent);
            }
            else if (i==R.id.signOutGoogle){
                signOut();
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



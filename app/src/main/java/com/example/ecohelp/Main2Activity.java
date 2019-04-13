package com.example.ecohelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends Activity implements View.OnClickListener {
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.create).setOnClickListener(this);
        findViewById(R.id.signinwithEmail).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser
                user = mAuth.getCurrentUser();
        updateUI(user);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(Main2Activity.this, Menu.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signInGoogle) {


        } else if (i == R.id.create) {
            Intent intent = new Intent(Main2Activity.this,Registation.class);
            startActivity(intent);

        } else if (i == R.id.signinwithEmail) {
            Intent intent = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}

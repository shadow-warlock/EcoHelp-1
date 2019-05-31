package com.example.Activities;

import android.content.Intent;

import android.support.v4.app.DialogFragment;

import android.os.Bundle;


import android.view.View;
import android.support.v7.widget.Toolbar;


import com.example.Classes.ChooseAvatarDialog;
import com.example.ecohelp.R;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsActivity extends BaseActivity {
    DialogFragment dlg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dlg1 = new ChooseAvatarDialog();
        Toolbar toolbar = findViewById(R.id.mytoolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Настройки");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button) {
            dlg1.show(getSupportFragmentManager(), "wdw");

        }
        if (i == R.id.button2) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("BackToRezerv", "0");
            startActivity(intent);
        }
        if (i == R.id.button3){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        }
    }


package com.example.Activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;


import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.Classes.ChooseAvatarDialog;
import com.example.ecohelp.R;
import com.google.firebase.auth.FirebaseAuth;



public class SettingsActivity extends BaseActivity{
    DialogFragment dlg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity = "Settings";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dlg1 = new ChooseAvatarDialog();
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        isOnline(SettingsActivity.this);
        Drawer(toolbar, SettingsActivity.this,SettingsActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Настройки");
        }



    }

    @Override
    public void onBackPressed() {

        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
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
        if (i == R.id.button3){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        }
    }


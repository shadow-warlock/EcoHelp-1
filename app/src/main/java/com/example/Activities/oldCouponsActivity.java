package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.ecohelp.R;

public class oldCouponsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_coupons);
        Activity = "oldCoupons";
        isOnline(oldCouponsActivity.this);
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Drawer(toolbar,oldCouponsActivity.this,oldCouponsActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Архив купонов");
        }
    }
}

package com.example.Activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.example.Classes.Dialogs.ChooseAvatarDialog;
import com.example.ecohelp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SettingsActivity extends BaseActivity {
    DialogFragment dlg1;
    TextView uId;
    ImageView avatarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity = "Settings";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        isOnline(SettingsActivity.this);
        Drawer(toolbar, SettingsActivity.this, SettingsActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Настройки");
        }
        uId = findViewById(R.id.uID);
        uId.setText("UID: " + getUid());
        avatarView = findViewById(R.id.avatarView);
        DatabaseReference rootRef2 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef2 = rootRef2.child("users").child(getUid());
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String Avatar = dataSnapshot.child("Avatar").getValue(String.class);

                if (Avatar != null) {
                    if (Avatar.equals("1")) {
                        avatar = (R.drawable.a1);
                    }
                    else if (Avatar.equals("2")) {
                        avatar = (R.drawable.a2);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef2.addListenerForSingleValueEvent(valueEventListener2);
        avatarView.setImageResource(avatar);
    }



            @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.changeAvatar) {
            dlg1 = new ChooseAvatarDialog();
            Bundle bundle = new Bundle();
            bundle.putString(getUid(),"uID");
            dlg1.setArguments(bundle);
            dlg1.show(getSupportFragmentManager(), "wdw");


        }
        if (i == R.id.exit) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}


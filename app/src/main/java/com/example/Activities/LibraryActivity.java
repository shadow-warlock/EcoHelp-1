package com.example.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.Classes.LibraryAdapter;
import com.example.Classes.couponsLibrary;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;



public class LibraryActivity extends BaseActivity {
    private static final String TAG = "LibraryActivity";
    private RecyclerView recyclerView;
    CheckBox checkBox;
    List<couponsLibrary> couponsLibraryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Activity = "Library";
        isOnline(LibraryActivity.this);

        RecyclerView recyclerView = findViewById(R.id.item);
        LibraryAdapter adapter = new LibraryAdapter(this, couponsLibraryList);
        recyclerView.setAdapter(adapter);
        setInitialData();

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Drawer(toolbar, LibraryActivity.this, LibraryActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Купоны");
        }
        checkBox = findViewById(R.id.checkBox2);
        checkBox.setVisibility(View.GONE);


    }


    public void setInitialData() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(getUid());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long numberCoupons = dataSnapshot.child("Coupons").child("NumberCoupons").getValue(Long.class);
                for (int i = 0; i < numberCoupons; i++) {

                    String info = dataSnapshot.child("Coupons").child(String.valueOf(i)).child("info").getValue(String.class);
                    String end = dataSnapshot.child("Coupons").child(String.valueOf(i)).child("end").getValue(String.class);
                    couponsLibraryList.add(new couponsLibrary(info, end));
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };
        uidRef.addValueEventListener(valueEventListener);

    }
}




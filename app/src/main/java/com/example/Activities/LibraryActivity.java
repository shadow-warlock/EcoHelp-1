package com.example.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import com.example.Classes.LibraryAdapter;
import com.example.Classes.Pojo.BarCode;
import com.example.Classes.Service;
import com.example.Classes.couponsLibrary;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LibraryActivity extends BaseActivity {
    private static final String TAG = "LibraryActivity";
    private RecyclerView recyclerView;
    CheckBox checkBox;
    List<couponsLibrary> couponsLibraryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        couponsLibraryList.add(new couponsLibrary("xaaxa", "qwer", "As"));
        couponsLibraryList.add(new couponsLibrary("xaaxa", "qwer", "As"));
        couponsLibraryList.add(new couponsLibrary("xaaxa", "qwer", "As"));
        couponsLibraryList.add(new couponsLibrary("xaaxa", "qwer", "As"));

        Activity = "Library";
        isOnline(LibraryActivity.this);

        recyclerView = findViewById(R.id.item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        LibraryAdapter adapter = new LibraryAdapter(this, couponsLibraryList);
        recyclerView.setAdapter(adapter);
//        setInitialData();

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Drawer(toolbar, LibraryActivity.this, LibraryActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Купоны");
        }
        couponsLibraryList.add(new couponsLibrary("xaaxa", "qwer", "As"));
        adapter.notifyDataSetChanged();




    }


//    public void setInitialData() {
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference uidRef = rootRef.child("users").child(getUid());
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Long numberCoupons = dataSnapshot.child("Coupons").child("NumberCoupons").getValue(Long.class);
//                for (int i = 0; i < numberCoupons;i++) {
//
//                    String info = dataSnapshot.child("Coupons").child(String.valueOf(i)).child("info").getValue(String.class);
//                    String end = dataSnapshot.child("Coupons").child(String.valueOf(i)).child("end").getValue(String.class);
//                    String id = dataSnapshot.child("Coupons").child(String.valueOf(i)).child("id").getValue(String.class);
//                    Log.v("RECYCLERVIEW",end);
//
//
//                    Service.getInstance().getJSONGetBarcodeByCouponApi().getBarcodeWithId(Integer.parseInt(id)).enqueue(new Callback<BarCode>() {
//                        @Override
//                        public void onResponse(Call<BarCode> call, Response<BarCode> response) {
//                            BarCode barCode = response.body();
//                            String tag = barCode.getImage();
//                            couponsLibraryList.add(new couponsLibrary(info, end, tag));
//                            recyclerView.getAdapter().notifyDataSetChanged();
//                        }
//
//                        @Override
//                        public void onFailure(Call<BarCode> call, Throwable t) {
//
//                        }
//                    });
//
//
//
//                }
//
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//
//        };
//        uidRef.addValueEventListener(valueEventListener);
//
//    }
}




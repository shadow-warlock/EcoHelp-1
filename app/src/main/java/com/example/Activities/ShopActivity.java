package com.example.Activities;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;




public class ShopActivity extends BaseActivity {
    AlertDialog.Builder ad;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ecohelp.R.layout.activity_shop);
         context = ShopActivity.this;
        String title = "Подтверждение";
        String message = "Вы уверены";
        String button1String = "Да ";
        String button2String = "Нет";

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, (dialog, arg1) -> {
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference uidRef = rootRef.child("users").child(getUid());
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @SuppressWarnings("ConstantConditions")
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                            long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                            if (coinsAmount >= 300) {
                                long petiarochkaAmount100 = snapshot.child("coupons").child("petiarochka").child("petiarochka100").getValue(Long.class);
                                Log.v("Shop1111111111111111", "" + petiarochkaAmount100);
                                uidRef.getRef().child("coupons").child("petiarochka").child("petiarochka100").setValue(petiarochkaAmount100 + 1);
                                uidRef.getRef().child("coinsAmount").setValue(coinsAmount-300);


                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Недостаточно монет", Toast.LENGTH_SHORT);
                                toast.show();
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }



                    };
                    uidRef.addListenerForSingleValueEvent(valueEventListener);
                });
        ad.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        ad.setCancelable(true);
        ad.setOnCancelListener(dialog -> {

        });
    }

    public void onClick(View v) {
        ad.show();
    }


}
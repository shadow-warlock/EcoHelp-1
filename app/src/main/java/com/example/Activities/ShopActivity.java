package com.example.Activities;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;




public class ShopActivity extends BaseActivity {
    AlertDialog.Builder adp100;
    AlertDialog.Builder adp300;
    AlertDialog.Builder adp500;
    AlertDialog.Builder adl100;
    AlertDialog.Builder adl300;
    AlertDialog.Builder adl500;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        context = ShopActivity.this;
        String title = "Подтверждение";
        String message = "Вы уверены";
        String button1String = "Да ";
        String button2String = "Нет";
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        adp100 = new AlertDialog.Builder(context);
        adp100.setTitle(title);  // заголовок
        adp100.setMessage(message); // сообщение
        adp100.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 100) {
                        long petiarochkaAmount100 = snapshot.child("coupons").child("petiarochka").child("petiarochka100").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + petiarochkaAmount100);
                        uidRef.getRef().child("coupons").child("petiarochka").child("petiarochka100").setValue(petiarochkaAmount100 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 100);


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
        adp100.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adp100.setCancelable(true);
        adp100.setOnCancelListener(dialog -> {

        });


        adp300 = new AlertDialog.Builder(context);
        adp300.setTitle(title);  // заголовок
        adp300.setMessage(message); // сообщение
        adp300.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 300) {
                        long petiarochkaAmount300 = snapshot.child("coupons").child("petiarochka").child("petiarochka300").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + petiarochkaAmount300);
                        uidRef.getRef().child("coupons").child("petiarochka").child("petiarochka300").setValue(petiarochkaAmount300 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 300);


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
        adp300.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adp300.setCancelable(true);
        adp300.setOnCancelListener(dialog -> {

        });
        adp500 = new AlertDialog.Builder(context);
        adp500.setTitle(title);  // заголовок
        adp500.setMessage(message); // сообщение
        adp500.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 500) {
                        long petiarochkaAmount500 = snapshot.child("coupons").child("petiarochka").child("petiarochka500").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + petiarochkaAmount500);
                        uidRef.getRef().child("coupons").child("petiarochka").child("petiarochka500").setValue(petiarochkaAmount500 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 500);


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
        adp500.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adp500.setCancelable(true);
        adp500.setOnCancelListener(dialog -> {

        });
        adl100 = new AlertDialog.Builder(context);
        adl100.setTitle(title);  // заголовок
        adl100.setMessage(message); // сообщение
        adl100.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 100) {
                        long lentaAmount100 = snapshot.child("coupons").child("lenta").child("lenta100").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + lentaAmount100);
                        uidRef.getRef().child("coupons").child("lenta").child("lenta100").setValue(lentaAmount100 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 100);


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
        adl100.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adl100.setCancelable(true);
        adl100.setOnCancelListener(dialog -> {

        });
        adl300 = new AlertDialog.Builder(context);
        adl300.setTitle(title);  // заголовок
        adl300.setMessage(message); // сообщение
        adl300.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 300) {
                        long lentaAmount300 = snapshot.child("coupons").child("lenta").child("lenta300").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + lentaAmount300);
                        uidRef.getRef().child("coupons").child("lenta").child("lenta300").setValue(lentaAmount300 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 300);


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
        adl300.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adl300.setCancelable(true);
        adl300.setOnCancelListener(dialog -> {

        });
        adl500 = new AlertDialog.Builder(context);
        adl500.setTitle(title);  // заголовок
        adl500.setMessage(message); // сообщение
        adl500.setPositiveButton(button1String, (dialog, arg1) -> {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("users").child(getUid());
            ValueEventListener valueEventListener = new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    long coinsAmount = snapshot.child("coinsAmount").getValue(Long.class);
                    if (coinsAmount >= 500) {
                        long lentaAmount500 = snapshot.child("coupons").child("lenta").child("lenta500").getValue(Long.class);
                        Log.v("Shop1111111111111111", "" + lentaAmount500);
                        uidRef.getRef().child("coupons").child("lenta").child("lenta500").setValue(lentaAmount500 + 1);
                        uidRef.getRef().child("coinsAmount").setValue(coinsAmount - 500);


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
        adl500.setNegativeButton(button2String, (dialog, arg1) -> {

        });
        adl500.setCancelable(true);
        adl500.setOnCancelListener(dialog -> {

        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.petiarochka100) {
            adp100.show();
        }
        if (i == R.id.petiarochka300) {
            adp300.show();
        }
        if (i == R.id.petiarochka500) {
            adp500.show();
        }
        if (i == R.id.lenta100) {
            adl100.show();
        }
        if (i == R.id.lenta300) {
            adl300.show();
        }
        if (i == R.id.lenta500) {
            adl500.show();
        }


    }
}
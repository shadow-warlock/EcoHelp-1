package com.example.Activities;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import com.example.Classes.SendMail;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class newPasswordActivity extends BaseActivity {
EditText mEmailField;


Button sendPassword1;
TextView sendPasswordSuccess1;
TextView wrongEmail;
EditText mPasswordField;

TextView chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Восстановление пароля");
        }
        sendPasswordSuccess1 = findViewById(R.id.textView11);
        wrongEmail = findViewById(R.id.textView10);


        chronometer = findViewById(R.id.chronometer);
        sendPassword1 = findViewById(R.id.sendPassword1);


        sendPasswordSuccess1.setVisibility(View.INVISIBLE);
        wrongEmail.setVisibility(View.INVISIBLE);

        mEmailField = findViewById(R.id.emailRecover);
        mPasswordField = findViewById(R.id.passwordRecover);
    }
    Boolean findSuccess=false;
    String uId;
    Boolean generateSuccess = false;
    String message;
    String  email;

    public void onClick(View v ){
        int id = v.getId();
        email = mEmailField.getText().toString().trim();
        String subject = "Восстановление пароля";

        if(id  == R.id.sendPassword1) {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
            Query query = ref.orderByChild("account").equalTo(email);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                        findSuccess=true;
                        uId = userSnapshot.getKey();

                        }
                    if(!findSuccess){
                        wrongEmail.setVisibility(View.VISIBLE);
                    }
                    else {

                        message = getRandomIntegerBetweenRange().trim();
                        SendMail sm = new SendMail(newPasswordActivity.this, email, subject, message);
                        sm.execute();
                        generateSuccess = true;



                        sendPasswordSuccess1.setVisibility(View.VISIBLE);
                        sendPassword1.setVisibility(View.INVISIBLE);


                        new CountDownTimer(120000, 1000) {


                            public void onTick(long millisUntilFinished) {
                                if (millisUntilFinished >= 60000 && millisUntilFinished != 120000) {
                                    int seconds = (int) ((millisUntilFinished - 60000) / 1000);
                                    if (seconds < 10) {
                                        chronometer.setText("Введите пароль" + " 1:0" + seconds);
                                    } else {
                                        chronometer.setText("Введите пароль " + "1:" + seconds);
                                    }
                                } else {
                                    int seconds = (int) millisUntilFinished / 1000;
                                    if (seconds < 10) {
                                        chronometer.setText("Введите пароль" + " 0:0" + seconds);
                                    } else {

                                        chronometer.setText("Введите пароль" + " 0:" + seconds);
                                    }

                                }

                            }

                            public void onFinish() {
                                chronometer.setVisibility(View.INVISIBLE);
                                sendPasswordSuccess1.setVisibility(View.INVISIBLE);
                                sendPassword1.setText("Выслать пароль ещё раз");
                                sendPassword1.setVisibility(View.VISIBLE);


                            }
                        }
                                .start();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            });




        }
     //   if(id==  ){
       //     String password = mPasswordField.getText().toString().trim();
         //   if(!password.equals(message) || !generateSuccess){
           //     showDialog("Пароль введён не правильно",newPasswordActivity.this);
           // }
            //else {
              //  Intent intent = new Intent(this,newNewPasswordActivity.class);
                //intent.putExtra("uID",uId);
               // intent.putExtra("email",email);
                //startActivity(intent);
                //finish();
            //}

        //}




    }

    public static String getRandomIntegerBetweenRange() {
        StringBuilder s = new StringBuilder();
        int max = 9;
        int min = 0;
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i <6 ; i++) {
            int randomInteger = min + rnd.nextInt(max - min + 1);
            s.append(randomInteger);
        }
        Log.v("RESYLT", String.valueOf(s));

        return s.toString();
    }
    }






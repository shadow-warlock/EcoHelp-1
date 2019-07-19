package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Classes.SendMail;
import com.example.ecohelp.R;

import java.util.Random;

public class newPasswordActivity extends BaseActivity {
EditText mEmailField;
boolean sendEmailFirst = false;
TextView sendPasswordSuccess1;
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
        sendPasswordSuccess1.setVisibility(View.INVISIBLE);

        mEmailField = findViewById(R.id.emailRecover);
    }

    public void onClick(View v ){
        int id = v.getId();
        String email = mEmailField.getText().toString().trim();


        if(id  == R.id.sendPassword1 && !sendEmailFirst) {
            sendEmailFirst = true;
            String subject = "Восстановление пароля";
            String message = getRandomIntegerBetweenRange().trim();
            SendMail sm = new SendMail(this, email, subject, message);
            sm.execute();
            sendPasswordSuccess1.setVisibility(View.VISIBLE);
        }
        else if(id == R.id.sendPassword1){
            showDialog("Пароль уже отправлен",newPasswordActivity.this);
        }

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






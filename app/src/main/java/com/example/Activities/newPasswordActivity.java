package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.Classes.SendMail;
import com.example.ecohelp.R;

public class newPasswordActivity extends AppCompatActivity {
EditText mEmailField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Восстановление пароля");
        }
        mEmailField = findViewById(R.id.emailRecover);
    }

    private void sendEmail() {
        //Getting content for email
        String email = mEmailField.getText().toString().trim();
        String subject = "Восстановление пароля";
        String message = getRandomIntegerBetweenRange().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
    public void onClick(View v ){
        sendEmail();
    }


    public static String getRandomIntegerBetweenRange() {

        int first = (int) (Math.random() * ((9 - 1) + 1)) + 1;
        int second = (int) (Math.random() * ((9 - 1) + 1)) + 1;
        int third = (int) (Math.random() * ((9 - 1) + 1)) + 1;
        int fourth = (int) (Math.random() * ((9 - 1) + 1)) + 1;
        int fiveth = (int) (Math.random() * ((9 - 1) + 1)) + 1;
        int sixth = (int) (Math.random() * ((9 - 1) + 1)) + 1;


        return ""+first+second+third+fourth+fiveth+sixth;
    }
    }






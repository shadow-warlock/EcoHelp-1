package com.example.Activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ecohelp.R;


import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class newNewPasswordActivity extends AppCompatActivity {
    private EditText PasswordField;
    private EditText RepeatPassword;
    String uid;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_new_password);
        RepeatPassword= findViewById(R.id.repeatPassword);
        PasswordField = findViewById(R.id.password);

        Intent intent = getIntent();

         uid = intent.getStringExtra("uID");
         email = intent.getStringExtra("email");
    }

    public void onClick(View v){
       String repeatPassword = RepeatPassword.getText().toString().trim();
       String password  = PasswordField.getText().toString().trim();
        if(repeatPassword.equals(password)){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

 // вместо password1234 надо ввести текущий пароль
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, "password1234");


            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            user.updatePassword(password).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Log.d("SUCCESS", "Password updated");
                                } else {
                                    Log.d("FAILED", "Error password not updated");
                                }
                            });
                        } else {
                            Log.d("FAILED", "Error auth failed");
                        }
                    });




        }
        else{
            PasswordField.setError("Пароли не совпадают");
            RepeatPassword.setError("Пароли не совпадают");
        }
    }
}

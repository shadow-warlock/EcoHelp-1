package com.example.Activities;


import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Classes.User;
import com.example.ecohelp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistationActivity extends BaseActivity {
    private static final String TAG = "EmailPassword";
    //Инициализация всего
    private EditText EmailField;
    private EditText PasswordField;
    private EditText RepeatPassword;
    private EditText Login;

    private DatabaseReference mDatabase;



    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        EmailField = findViewById(R.id.inputEmail);
        PasswordField = findViewById(R.id.inputPassword);
        RepeatPassword = findViewById(R.id.repeatPassword);
        Login = findViewById(R.id.inputLogin);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();



    }

    private void createAccount(String email, String password) {


        Log.d(TAG, "Создание аккаунта" + email);
            if (validateForm()) {
                return;
            }


            showProgressDialog();

            //Регистрация через емайл

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    hideProgressDialog();
                    Log.d(TAG, "Аккаунт успешно создан");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    assert user != null;

                    String login = Login.getText().toString();
                    writeNewUser(getUid(),email,login);
                } else {
                    hideProgressDialog();
                    Log.w(TAG, "Ошибка создания аккаунта", task.getException());
                    Toast.makeText(RegistationActivity.this, "Ошибка создания аккаунта",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            });
        }







    private boolean validateForm() {
        boolean valid = true;

        String email = EmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            EmailField.setError("Пусто");
            valid = false;
        } else {
            EmailField.setError(null);
        }

        String password = PasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            PasswordField.setError("Пусто");
            valid = false;
        } else {
            PasswordField.setError(null);
        }
        String repeatPassword = RepeatPassword.getText().toString();
        if(TextUtils.isEmpty(repeatPassword)){
            RepeatPassword.setError("Пусто");
        }
        else{
            RepeatPassword.setError(null);
        }
        String login = Login.getText().toString();
        if(TextUtils.isEmpty(login)){
            Login.setError("Пусто");
        }
        else {
            Login.setError(null);
        }

        return !valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(RegistationActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();

        }
    }
    private void writeNewUser(String userId, String email, String name) {

        User user = new User(name,email, 0);

        mDatabase.child("users").child(userId).setValue(user);
        mDatabase.child("users").child(userId).child("coupons").child("petiarochka").child("petiarochka100").setValue(0);
        mDatabase.child("users").child(userId).child("coupons").child("petiarochka").child("petiarochka300").setValue(0);
        mDatabase.child("users").child(userId).child("coupons").child("petiarochka").child("petiarochka500").setValue(0);
        mDatabase.child("users").child(userId).child("coupons").child("lenta").child("lenta100").setValue(0);
        mDatabase.child("users").child(userId).child("coupons").child("lenta").child("lenta300").setValue(0);
        mDatabase.child("users").child(userId).child("coupons").child("lenta").child("lenta500").setValue(0);
    }
    public void Onclick(View v){
        String repeatPassword =  RepeatPassword.getText().toString();
        String password =  PasswordField.getText().toString();
        if (password.equals(repeatPassword)){

            createAccount(EmailField.getText().toString(), PasswordField.getText().toString());

    }
    else {
            PasswordField.setError("Пароли не совпадают");
            RepeatPassword.setError("Пароли не совпадают");
        }

    }
}

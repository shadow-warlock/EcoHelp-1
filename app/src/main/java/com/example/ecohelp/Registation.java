package com.example.ecohelp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registation extends Activity {
    private static final String TAG = "EmailPassword";
    //Инициализация всего
    private EditText EmailField;
    private EditText PasswordField;
    private EditText RepeatPassword;
    private EditText Login;
    public ProgressDialog pd;
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
            pd = new ProgressDialog(this);

            pd.show();
            pd.setMessage("Регистрация");

            //Регистрация через емайл

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    pd.hide();
                    Log.d(TAG, "Аккаунт успешно создан");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    assert user != null;
                    String uID = user.getUid();
                    String login = Login.getText().toString();
                    writeNewUser(uID,email,login);
                } else {
                    pd.hide();
                    Log.w(TAG, "Ошибка создания аккаунта", task.getException());
                    Toast.makeText(Registation.this, "Ошибка создания аккаунта",
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

        return !valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(Registation.this, Menu.class);
            startActivity(intent);
            finish();

        }
    }
    private void writeNewUser(String userId,String email,String name) {

        User user = new User(name,email);

        mDatabase.child("users").child(userId).setValue(user);
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

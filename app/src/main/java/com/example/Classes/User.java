package com.example.Classes;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String email;
    public int coinsAmount;

    public User() {

    }

    public User(String email,int coinsAmount) {

        this.email = email;
        this.coinsAmount = coinsAmount;
    }

}
package com.example.Classes;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public int coinsAmount;

    public User() {

    }

    public User(String username, String email,int coinsAmount) {
        this.username = username;
        this.email = email;
        this.coinsAmount = coinsAmount;
    }

}
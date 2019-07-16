package com.example.Classes;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String account;
    public int coinsAmount;

    public User() {

    }

    public User(String account,int coinsAmount) {

        this.account = account;
        this.coinsAmount = coinsAmount;
    }

}
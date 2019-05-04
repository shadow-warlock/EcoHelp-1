package com.example.Classes;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Coupons {

    private int logo;


    public Coupons(int logo) {

        this.logo = logo;
    }

    public int getLogo(){
        return this.logo;
    }
    public void setLogo(int logo){
        this.logo = logo;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("logo", logo);


        return result;
    }

}

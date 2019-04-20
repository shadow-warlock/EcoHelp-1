package com.example.Classes;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Coupons {
    private String name;
    private int logo;
    private String sum;

    public Coupons(String name, String sum, int logo) {
        this.name = name;
        this.sum = sum;
        this.logo = logo;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSum(){
        return this.sum;

    }
    public void setSum(String sum){
        this.sum = sum;
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
        result.put("name", name);
        result.put("sum", sum);
        result.put("logo", logo);


        return result;
    }

}

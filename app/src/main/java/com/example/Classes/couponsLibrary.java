package com.example.Classes;

public class couponsLibrary {
    private String infoAboutCoupon;
    private String end;

    public couponsLibrary(String infoAboutCoupon,String end){
        this.infoAboutCoupon = infoAboutCoupon;
        this.end = end;
    }
    public String getInfoAboutCoupon(){
        return this.infoAboutCoupon;
    }
    public String getEnd(){
        return this.end;
    }
    public  void setEnd(String end){
        this.end = end;
    }
    public void setInfoAboutCoupon(String infoAboutCoupon){
        this.infoAboutCoupon = infoAboutCoupon;
    }
}

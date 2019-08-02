package com.example.Classes;

public class couponsLibrary {
    private String infoAboutCoupon;
    private String end;
    private String tag;

    public couponsLibrary(String infoAboutCoupon,String end,String tag){

        this.infoAboutCoupon = infoAboutCoupon;
        this.end = end;
        this.tag = tag;
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
    public  String getTag(){
        return this.tag;
    }
    public void setTag(){
        this.tag = tag;
    }
}

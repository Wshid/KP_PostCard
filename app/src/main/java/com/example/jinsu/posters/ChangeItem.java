package com.example.jinsu.posters;

/**
 * Created by jinsu on 2018-03-27.
 */

public class ChangeItem {
    private String ch_txt_name;
    private String ch_txt_detail;
    private String ch_txt_detail2;
    private int ch_drawable;
    private boolean isCheked;
    private String gift_key;

    public ChangeItem(String name, String detail1, String detail2,int drawable, boolean isCheked, String gift_key)
    {
        this.ch_txt_name=name;
        this.ch_txt_detail=detail1;
        this.ch_txt_detail2=detail2;
        this.ch_drawable=drawable;
        this.isCheked=isCheked;
        this.gift_key=gift_key;
    }
    public void setCh_txt_name(String ch_txt_name) {
        this.ch_txt_name = ch_txt_name;
    }

    public void setCh_txt_detail(String ch_txt_detail) {
        this.ch_txt_detail = ch_txt_detail;
    }

    public void setCh_txt_detail2(String ch_txt_detail2) {
        this.ch_txt_detail2 = ch_txt_detail2;
    }

    public String getGift_key() {
        return gift_key;
    }

    public void setCh_drawable(int ch_drawable) {
        this.ch_drawable = ch_drawable;
    }

    public void setCheked(boolean cheked) {
        isCheked = cheked;
    }

    public String getCh_txt_name() {

        return ch_txt_name;
    }

    public String getCh_txt_detail() {
        return ch_txt_detail;
    }

    public String getCh_txt_detail2() {
        return ch_txt_detail2;
    }

    public int getCh_drawable() {
        return ch_drawable;
    }

    public boolean isCheked() {
        return isCheked;
    }



}

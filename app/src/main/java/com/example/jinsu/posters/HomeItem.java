package com.example.jinsu.posters;

/**
 * Created by jinsu on 2018-03-26.
 */

public class HomeItem {
    private String txt_name;
    private String txt_detail;
    private String txt_detail2;
    private int drawable;

    public HomeItem(String txt_name, String txt_detail, String txt_detail2, int drawable)
    {
        this.txt_name=txt_name;
        this.txt_detail=txt_detail;
        this.txt_detail2=txt_detail2;
        this.drawable=drawable;
    }

    public void setTxt_detail2(String txt_detail2) {
        this.txt_detail2 = txt_detail2;
    }

    public String getTxt_detail2() {

        return txt_detail2;
    }





    public void setTxt_name(String txt_name) {
        this.txt_name = txt_name;
    }

    public void setTxt_detail(String txt_detail) {
        this.txt_detail = txt_detail;
    }



    public String getTxt_name() {

        return txt_name;

    }

    public int getDrawable() {
        return drawable;
    }

    public String getTxt_detail() {
        return txt_detail;
    }

}

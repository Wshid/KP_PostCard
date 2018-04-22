package com.example.jinsu.posters.Model;

public class Gift {

    private String gitt_idx;
    private String gift_title;
    private String gift_content;
    private String gift_category;

    public Gift(String gitt_key, String gift_title, String gift_content,String gift_category) {
        this.gitt_idx = gitt_key;
        this.gift_title = gift_title;
        this.gift_content = gift_content;
        this.gift_category = gift_category;
    }

    public String getGitt_idx() {
        return gitt_idx;
    }

    public String getGift_category() {
        return gift_category;
    }

    public String getGift_title() {
        return gift_title;
    }

    public String getGift_content() {
        return gift_content;
    }
}

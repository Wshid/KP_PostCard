package com.example.jinsu.posters.Model;

public class Gift {

    private String gift_idx;
    private String gift_title;
    private String gift_content;
    private String gift_category;
    private String gift_limit;
    private String gift_image;

    public String getGift_idx() {
        return gift_idx;
    }

    public String getGift_title() {
        return gift_title;
    }

    public String getGift_content() {
        return gift_content;
    }

    public String getGift_category() {
        return gift_category;
    }

    public String getGift_limit() {
        return gift_limit;
    }

    public String getGift_image() {
        return gift_image;
    }

    public Gift(String gift_idx, String gift_title, String gift_content, String gift_category, String gift_limit, String gift_image) {

        this.gift_idx = gift_idx;
        this.gift_title = gift_title;
        this.gift_content = gift_content;
        this.gift_category = gift_category;
        this.gift_limit = gift_limit;
        this.gift_image = gift_image;
    }
}

package com.example.jinsu.posters.Model;

public class MyUser {
    String card_key;
    String gift_1;
    String gift_2;
    String gift_3;
    String gift_4;
    String gift_5;

    public MyUser(String card_key, String gift_1, String gift_2, String gift_3, String gift_4, String gift_5) {
        this.card_key = card_key;
        this.gift_1 = gift_1;
        this.gift_2 = gift_2;
        this.gift_3 = gift_3;
        this.gift_4 = gift_4;
        this.gift_5 = gift_5;
    }

    public String getCard_key() {
        return card_key;
    }

    public String getGift_1() {
        return gift_1;
    }

    public String getGift_2() {
        return gift_2;
    }

    public String getGift_3() {
        return gift_3;
    }

    public String getGift_4() {
        return gift_4;
    }

    public String getGift_5() {
        return gift_5;
    }

    public void chGift_1(String key)
    {
        gift_1 = key;
    }
    public void chGift_2(String key)
    {
        gift_2 = key;
    }
    public void chGift_3(String key)
    {
        gift_3 = key;
    }
    public void chGift_4(String key)
    {
        gift_1 = key;
    }
    public void chGift_5(String key)
    {
        gift_1 = key;
    }

}

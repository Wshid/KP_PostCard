package com.example.jinsu.posters.Model;

public class MyUser {
    String card_key;
    int gift_1;
    int gift_2;
    int gift_3;

    public MyUser(String card_key, int gift_1, int gift_2, int gift_3) {
        this.card_key = card_key;
        this.gift_1 = gift_1;
        this.gift_2 = gift_2;
        this.gift_3 = gift_3;
    }

    public String getCard_key() {
        return card_key;
    }

    public int getGift_1() {
        return gift_1;
    }

    public int getGift_2() {
        return gift_2;
    }

    public int getGift_3() {
        return gift_3;
    }

    public void chGift_1(int key)
    {
        gift_1 = key;
    }
    public void chGift_2(int key)
    {
        gift_2 = key;
    }
    public void chGift_3(int key)
    {
        gift_3 = key;
    }

}

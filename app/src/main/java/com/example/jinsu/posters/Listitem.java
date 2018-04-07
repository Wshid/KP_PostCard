package com.example.jinsu.posters;

public class Listitem {
    private String date;
    private String place;
    private String sum;
    private String rest;
    private boolean money;

    public Listitem(String date, String place, String sum, String rest, boolean money) {
        this.date = date;
        this.place = place;
        this.sum = sum;
        this.rest = rest;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getSum() {
        return sum;
    }

    public String getRest() {
        return rest;
    }

    public boolean isMoney() {
        return money;
    }
}

package com.example.asus.firebaserealtimecustomlistview;

/**
 * Created by Asus on 15/04/2018.
 */

public class ConVat {
    private String Ten;
    private Integer SoChan;

    public ConVat() {
    }

    public ConVat(String ten, Integer soChan) {
        Ten = ten;
        SoChan = soChan;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Integer getSoChan() {
        return SoChan;
    }

    public void setSoChan(Integer soChan) {
        SoChan = soChan;
    }
}

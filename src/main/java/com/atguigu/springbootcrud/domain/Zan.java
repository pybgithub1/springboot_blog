package com.atguigu.springbootcrud.domain;

public class Zan {

    private int bid;
    private int number;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getNum() {
        return number;
    }

    public void setNum(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Like{" +
                "bid=" + bid +
                ", number=" + number +
                '}';
    }
}

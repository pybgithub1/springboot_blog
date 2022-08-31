package com.atguigu.springbootcrud.domain;

public class CommentNum {
    private int bid;
    private int number;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CommentNum{" +
                "bid=" + bid +
                ", number=" + number +
                '}';
    }
}

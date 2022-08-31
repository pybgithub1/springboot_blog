package com.atguigu.springbootcrud.domain;

public class Focus {

    private int id;
    private String user1;
    private String user2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    @Override
    public String toString() {
        return "focus{" +
                "id=" + id +
                ", user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                '}';
    }
}

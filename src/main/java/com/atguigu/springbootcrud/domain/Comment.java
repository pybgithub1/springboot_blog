package com.atguigu.springbootcrud.domain;

public class Comment {
    private int id;
    private int bid;
    private String user;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBId() {
        return bid;
    }

    public void setBId(int bid) {
        this.bid = bid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", bid=" + bid +
                ", user='" + user + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

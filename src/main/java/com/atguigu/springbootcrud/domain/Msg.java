package com.atguigu.springbootcrud.domain;

public class Msg {
    private int mid;
    private String fromuser;
    private String touser;
    private String msg;
    private String time;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "mid=" + mid +
                ", fromuser='" + fromuser + '\'' +
                ", touser='" + touser + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

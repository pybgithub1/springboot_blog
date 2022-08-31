package com.atguigu.springbootcrud.util;


import java.util.Random;

public class VerifyCode {

    public String verify() {
        Random random = new Random();
        int i = random.nextInt();
        int s = i > 0 ? i : -i;
        int i1 = s % 1000000;
        String s1 = String.valueOf(i1);
        return s1;
    }

}

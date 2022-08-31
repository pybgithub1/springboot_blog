package com.atguigu.springbootcrud.service;
import com.atguigu.springbootcrud.domain.Admin;
import com.atguigu.springbootcrud.domain.User;

public interface LoginService {


    Admin findByAdminName(String name);

    User findByUserName(String name);
}

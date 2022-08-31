package com.atguigu.springbootcrud.service.impl;

import com.atguigu.springbootcrud.domain.Admin;
import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.mapper.LoginMapper;
import com.atguigu.springbootcrud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;



    @Override
    public Admin findByAdminName(String name) {
        Admin admin = loginMapper.findByAdminName(name);
        return admin;
    }

    @Override
    public User findByUserName(String name) {
        User user = loginMapper.findByUserName(name);
        return user;
    }
}

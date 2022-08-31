package com.atguigu.springbootcrud.service.impl;


import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.mapper.RegisterMapper;
import com.atguigu.springbootcrud.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper  registerMapper;

    @Override
    public void save(User user) {
        registerMapper.save(user.getName(),user.getPassword());
    }


}

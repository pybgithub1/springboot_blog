package com.atguigu.springbootcrud.service.impl;

import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.mapper.UserMapper;
import com.atguigu.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Boolean save(User user) {
        return userMapper.save(user.getUid(),user.getName());
    }

    @Override
    public Boolean delete(User user) {
        return userMapper.delete(user.getName());
    }
}

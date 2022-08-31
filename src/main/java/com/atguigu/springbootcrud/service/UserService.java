package com.atguigu.springbootcrud.service;

import com.atguigu.springbootcrud.domain.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User findByName(String name);

    Boolean save(User user);

    Boolean delete(User user);
}

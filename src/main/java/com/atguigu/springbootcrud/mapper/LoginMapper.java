package com.atguigu.springbootcrud.mapper;

import com.atguigu.springbootcrud.domain.Admin;
import com.atguigu.springbootcrud.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select(value = "select * from admin where name=#{name}")
    public Admin findByAdminName(String name);

    @Select(value = "select * from user where name=#{name}")
    public User findByUserName(String name);

}

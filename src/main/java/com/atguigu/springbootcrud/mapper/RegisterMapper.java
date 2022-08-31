package com.atguigu.springbootcrud.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {

    @Insert("insert into user(name,password) values(#{name},#{password})")
    public void save(String name,String password);
}

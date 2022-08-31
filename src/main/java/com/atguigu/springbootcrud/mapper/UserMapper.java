package com.atguigu.springbootcrud.mapper;

import com.atguigu.springbootcrud.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> listAll();

    @Select("select * from silenced where name=#{name}")
    User findByName(String name);

    @Insert("insert into silenced(uid,name) values(#{uid},#{name})")
    Boolean save(int uid,String name);

    @Delete("delete from silenced where name=#{name}")
    Boolean delete(String name);
}
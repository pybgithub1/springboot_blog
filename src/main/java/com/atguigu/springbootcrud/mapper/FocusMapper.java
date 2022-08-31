package com.atguigu.springbootcrud.mapper;

import com.atguigu.springbootcrud.domain.Focus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface FocusMapper {
    @Insert("insert into focus (user1,user2) values (#{user1},#{user2})")
    Boolean insertFocus(String user1,String user2);

    @Select("select * from focus where user1=#{user1} and user2=#{user2}")
    Focus listFocusByAuthorAndFocus(String user1, String user2);

    @Delete("delete from focus where user1=#{user1} and user2=#{user2}")
    Boolean deleteFocus(String user1,String user2);

    @Select("select * from focus where user1=#{user1}")
    List<Focus> listFocusByAuthor(String user1);
}

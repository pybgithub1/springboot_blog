package com.atguigu.springbootcrud.mapper;

import com.atguigu.springbootcrud.domain.Msg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MsgMapper {

    @Select("select * from msg where fromuser=#{fromuser} and touser=#{touser}")
    List<Msg> findMsgByFromuserAndTouser(String fromuser, String touser);

    @Insert("insert into msg (fromuser,touser,msg,time) values (#{fromuser},#{touser},#{msg},#{time})")
    Boolean insertMsgByFromuserAndTouserAndMsg(String fromuser, String touser, String msg, String time);
}

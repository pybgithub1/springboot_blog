package com.atguigu.springbootcrud.service;

import com.atguigu.springbootcrud.domain.Msg;

import java.util.List;

public interface MsgService {

    List<Msg> findMsgByFromuserAndTouser(String fromuser,String touser);

    Boolean insertMsgByFromuserAndTouserAndMsg(String fromuser, String touser, String msg, String time);

    List<Msg> findMsgsGunDongBack(String fromuser,String touser,String mid);

    List<Msg> findMsgsGunDongForword(String fromuser,String touser,String mid);
}

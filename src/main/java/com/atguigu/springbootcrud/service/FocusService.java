package com.atguigu.springbootcrud.service;

import com.atguigu.springbootcrud.domain.Focus;

import java.util.List;


public interface FocusService {

    Boolean insertFocus(Focus focus);

    Focus listFocusByAuthorAndFocus(String user1,String user2);

    Boolean deleteFocus(Focus focus);

    List<Focus> listFocusByAuthor(String user1);
}

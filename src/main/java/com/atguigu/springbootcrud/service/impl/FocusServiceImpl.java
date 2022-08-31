package com.atguigu.springbootcrud.service.impl;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Focus;
import com.atguigu.springbootcrud.mapper.FocusMapper;
import com.atguigu.springbootcrud.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("FocusService")
public class FocusServiceImpl implements FocusService {

    @Autowired
    private FocusMapper focusMapper;

    @Override
    public Boolean insertFocus(Focus focus) {
        return focusMapper.insertFocus(focus.getUser1(), focus.getUser2());
    }

    @Override
    public Focus listFocusByAuthorAndFocus(String user1,String user2) {
        return focusMapper.listFocusByAuthorAndFocus(user1,user2);
    }

    @Override
    public Boolean deleteFocus(Focus focus) {
        return focusMapper.deleteFocus(focus.getUser1(), focus.getUser2());
    }

    @Override
    public List<Focus> listFocusByAuthor(String user1) {
        return focusMapper.listFocusByAuthor(user1);
    }


}

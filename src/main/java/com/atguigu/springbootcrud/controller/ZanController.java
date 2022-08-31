package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Focus;
import com.atguigu.springbootcrud.domain.Zan;
import com.atguigu.springbootcrud.service.BlogService;
import com.atguigu.springbootcrud.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/like")
public class ZanController {

    @Autowired
    private BlogService blogService;

    //处理管理员和用户点击想要点赞的文章请求
    @ResponseBody
    @RequestMapping(value = "/liked/{bid}")
    public String liked(@PathVariable("bid") String bid) {
        Zan zan = blogService.showZanNum(Integer.parseInt(bid));
        int number = zan.getNum();
        number += 1;
        blogService.updateZanNum(Integer.parseInt(bid), number);
        return String.valueOf(number);
    }

    //处理管理员和用户点击想要取消点赞的文章请求
    @ResponseBody
    @RequestMapping(value = "/unliked/{bid}")
    public String unliked(@PathVariable("bid") String bid) {
        Zan zan = blogService.showZanNum(Integer.parseInt(bid));
        int number = zan.getNum();
        number -= 1;
        blogService.updateZanNum(Integer.parseInt(bid), number);
        return String.valueOf(number);
    }


}

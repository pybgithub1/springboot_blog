package com.atguigu.springbootcrud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/blogIndex")
public class BlogIndexController {

    //处理默认的博客首页请求，跳转到主页面main页面
    @RequestMapping("/mainIndex")
    public String mainIndex() {
        //跳转到默认主页面
        return "blogMain";
    }


}

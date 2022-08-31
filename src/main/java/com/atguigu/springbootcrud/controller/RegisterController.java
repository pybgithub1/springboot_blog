package com.atguigu.springbootcrud.controller;


import com.atguigu.springbootcrud.domain.Focus;
import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.service.FocusService;
import com.atguigu.springbootcrud.service.LoginService;
import com.atguigu.springbootcrud.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private FocusService focusService;

    //处理用户注册请求，首先跳转到注册页面
    @RequestMapping(value = "/registerUi")
    public String loginUi() {
        //跳转到注册页面
        return "userRegister";
    }

    //处理用户注册请求，注册成功返回登录页面，注册失败则返回注册页面
    @RequestMapping(value = "/registerIn")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        Model model) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        //注册新用户的同时，如果注册成功，则新用户应该关注管理员，因为管理员可以发布一些公开对所有人要说明的规则
        //这个地方先准备关注数据focus,下面注册成功后会用到
        Focus focus=new Focus();
        focus.setUser1(name);
        focus.setUser2("13656641499");
        User user = loginService.findByUserName(name);
        if(newUser.getName()==""||newUser.getPassword()==""){
            model.addAttribute("error", "您输入的用户名或者密码为空，请重新输入！");
            //注册失败后再次返回注册页面进行注册
            return "userRegister";
        }else{
            if (user != null) {
                model.addAttribute("error", "您注册的用户名已存在，请选择别的名称进行注册！");
                //注册失败后再次返回注册页面进行注册
                return "userRegister";
            } else {
                registerService.save(newUser);
                //注册成功后把关注数据持久化到数据库
                focusService.insertFocus(focus);
                //注册成功后返回登录页面进行登录
                return "userLogin";
            }
        }
    }


}

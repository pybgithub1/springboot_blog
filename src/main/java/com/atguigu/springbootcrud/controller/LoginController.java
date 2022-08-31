package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Admin;
import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.service.LoginService;
import com.atguigu.springbootcrud.service.UserService;
import com.atguigu.springbootcrud.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    //处理管理员登录请求，首先跳转到登录页面
    @RequestMapping(value = "/loginAdminUi")
    public String loginAdminUi(Model model, HttpSession session) {
        VerifyCode verifyCode = new VerifyCode();
        String verify = verifyCode.verify();
        model.addAttribute("verify", verify);
        session.setAttribute("code", verify);
        //跳转到登录页面
        return "adminLogin";
    }

    //处理管理员登录请求，登录成功后跳转到主页面main,登录失败后跳回到登录页面
    @RequestMapping(value = "/loginAdminIn", method = RequestMethod.POST)
    public String loginAdminIn(@RequestParam("name") String name,
                               @RequestParam("password") String password,
                               @RequestParam("input") String input,
                               Model model, HttpSession session) {

        String code = (String) session.getAttribute("code");
        Admin admin = loginService.findByAdminName(name);
        if (admin != null && (admin.getPassword().equals(password)) && input.equals(code) && (input != null)) {
            model.addAttribute("msg", name);
            session.setAttribute("author", name);

            //登录成功后跳转到主页面
            return "adminMain";
        } else if (admin != null && (admin.getPassword().equals(password))) {
            model.addAttribute("error", "验证码错误或未输入！请刷新后重新输入");
        } else {
            model.addAttribute("error", "输入的用户不存在或密码错误！请重新输入");
        }

        //登录失败跳转到登录页面进行登录
        return "adminLogin";

    }


    //处理用户登录请求，首先跳转到登录页面
    @RequestMapping(value = "/loginUserUi")
    public String loginUserUi(Model model, HttpSession session) {
        VerifyCode verifyCode = new VerifyCode();
        String verify = verifyCode.verify();
        //保存生成的验证码到model，携带到前端页面
        model.addAttribute("verify", verify);
        //保存后台生成的验证码
        session.setAttribute("code", verify);
        //跳转到登录页面
        return "userLogin";
    }

    //处理用户登录请求，登录成功后跳转到主页面main,登录失败后跳回到登录页面
    @RequestMapping(value = "/loginUserIn", method = RequestMethod.POST)
    public String loginUserIn(@RequestParam("name") String name,
                              @RequestParam("password") String password,
                              @RequestParam("input") String input,
                              Model model, HttpSession session) {
        //取出后台生成的验证码，准备与前端传进来的验证码进行验证
        String code = (String) session.getAttribute("code");
        //从用户表中查找当前要登录的用户信息
        User user = loginService.findByUserName(name);
        //从禁言表中查找当前要登录的用户信息,查到则跳转到主页面main.html,查询不到则进行正常的登录验证
        User user1 = userService.findByName(name);
        if (user1==null) {
            if (user != null && (user.getPassword().equals(password)) && input.equals(code) && (input != null)) {
                model.addAttribute("msg", name);
                session.setAttribute("author", name);
                //登录成功后跳转到主页面
                return "userMain";
            } else if (user != null && (user.getPassword().equals(password))) {
                model.addAttribute("error", "验证码错误或未输入！请刷新后重新输入");
            } else {
                model.addAttribute("error", "输入的用户不存在或密码错误！请重新输入");
            }
            //登录失败跳转到登录页面进行登录
            return "userLogin";
        } else {
            return "blogMain";
        }

    }


}

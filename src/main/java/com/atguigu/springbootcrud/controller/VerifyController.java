package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.util.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/verify")
public class VerifyController {

    /* 获取数字验证码*/
    @RequestMapping("/getVerifyCodeAdmin")
    public String getVerifyCodeAdmin(Model model, HttpSession session) {
        VerifyCode verifyCode = new VerifyCode();
        String verify = verifyCode.verify();
        model.addAttribute("verify",verify);
        session.setAttribute("code",verify);
        return "adminLogin";
    }

    /* 获取数字验证码*/
    @RequestMapping("/getVerifyCodeUser")
    public String getVerifyCodeUser(Model model, HttpSession session) {
        VerifyCode verifyCode = new VerifyCode();
        String verify = verifyCode.verify();
        model.addAttribute("verify",verify);
        session.setAttribute("code",verify);
        return "userLogin";
    }


}

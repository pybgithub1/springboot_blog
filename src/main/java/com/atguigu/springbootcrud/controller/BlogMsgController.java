package com.atguigu.springbootcrud.controller;


import com.atguigu.springbootcrud.domain.Msg;
import com.atguigu.springbootcrud.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/blogmsg")
public class BlogMsgController {

    @Autowired
    private MsgService msgService;

    //处理未登陆者博客消息请求
    @RequestMapping("/blogSelect")
    public String select() {
        return "blogSelect";
    }


    //处理登录者聊天的请求
    @RequestMapping("/chat/{name}")
    public String chat(@PathVariable String name, Model model) {
        try {
            log.info("跳转到chat的页面上");
            model.addAttribute("username", name);
            return "chat";
        } catch (Exception e) {
            log.info("跳转到chat的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }


    }

    //处理登录者请求与想要聊天者历史聊天记录的请求
    @ResponseBody
    @RequestMapping("/msg")
    public List<Msg> msg(@RequestParam String fromuser, @RequestParam String touser) {
        List<Msg> msgs = new ArrayList<>();
        List<Msg> msgsend = new ArrayList<>();
        List<Msg> msgs1 = msgService.findMsgByFromuserAndTouser(fromuser, touser);
        List<Msg> msgs2 = msgService.findMsgByFromuserAndTouser(touser, fromuser);
        msgs.addAll(msgs1);
        msgs.addAll(msgs2);
        msgs.sort(new Comparator<Msg>() {
            @Override
            public int compare(Msg msg1, Msg msg2) {
                return msg1.getTime().compareTo(msg2.getTime());
            }
        });
        if (msgs.size() >= 7) {
            for (int i = msgs.size() - 7; i < msgs.size(); i = i + 1) {
                msgsend.add(i - (msgs.size() - 7), msgs.get(i));
            }
            return msgsend;
        } else {
            return msgs;
        }


    }


    //处理登录者请求与想要聊天者滚动历史聊天记录的请求
    @ResponseBody
    @RequestMapping("/gundongmsg")
    public List<Msg> gundongmsg(@RequestParam String fromuser, @RequestParam String touser, @RequestParam String status, @RequestParam String mid) {
        System.out.println(status);
        System.out.println(mid);
        if(status.equals("top")){
            //上滚动，往以前追溯历史聊天记录
            List<Msg> list = msgService.findMsgsGunDongBack(fromuser, touser, mid);
            return list;
        }else{
            //下滚动，往后追溯历史聊天记录
            List<Msg> list = msgService.findMsgsGunDongForword(fromuser, touser, mid);
            return list;
        }

    }


    //处理登录者请求与想要聊天者历史聊天记录的请求
    @ResponseBody
    @RequestMapping("/msgnum")
    public int msgnum(@RequestParam String fromuser, @RequestParam String touser) {
        List<Msg> msgs = new ArrayList<>();
        List<Msg> msgs1 = msgService.findMsgByFromuserAndTouser(fromuser, touser);
        List<Msg> msgs2 = msgService.findMsgByFromuserAndTouser(touser, fromuser);
        msgs.addAll(msgs1);
        msgs.addAll(msgs2);
        return msgs.size();
    }

}

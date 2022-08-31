package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Focus;
import com.atguigu.springbootcrud.service.BlogService;
import com.atguigu.springbootcrud.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/myfocus")
public class MyfocusController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FocusService focusService;

    //处理未登陆者全部博客请求
    @RequestMapping("/blogSelect")
    public String select() {
        return "blogSelect";
    }


    //处理管理员和用户点击想要关注者的名字索引请求
    @RequestMapping(value = "/mayFoucsUser/{author}")
    public String Index(@PathVariable("author") String author,
                         HttpSession session,Model model) {
        //在session中获取当前登录用户的名字，也是为author，上面的author为可能要关注的用户的名字，两者不一样
        String user = (String) session.getAttribute("author");
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        Focus focus = focusService.listFocusByAuthorAndFocus(user, author);
        if(focus==null){
            model.addAttribute("focus","关注");
        }else{
            model.addAttribute("focus","取消关注");
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("name", author);
        return "userMsgAndBlogList";
    }

    //处理管理员和用户进行关注的请求
    @ResponseBody
    @RequestMapping(value = "/focus/{name}")
    public String focus(@PathVariable("name") String user2,
                        Model model, HttpSession session) {
        //首先进行关注操作，把登录用户与被关注用户封装成focus对象进行持久化
        String user1 = (String) session.getAttribute("author");
        Focus focus = new Focus();
        focus.setUser1(user1);
        focus.setUser2(user2);
        Focus focus1 = focusService.listFocusByAuthorAndFocus(user1, user2);
        if (focus1 == null) {
            System.out.println("我还没有关注她");
            focusService.insertFocus(focus);
            System.out.println("我刚刚关注了她");
        } else {
            System.out.println("我已经关注了她");
        }
        return "取消关注";
    }

    //处理管理员和用户取消关注的请求
    @ResponseBody
    @RequestMapping(value = "/quitFocus1/{name}")
    public String quitfocus1(@PathVariable("name") String user2,
                             Model model, HttpSession session) {
        //首先进行准备关注操作，把登录用户与被关注用户封装成focus对象
        String user1 = (String) session.getAttribute("author");
        Focus focus = new Focus();
        focus.setUser1(user1);
        focus.setUser2(user2);
        Focus focus1 = focusService.listFocusByAuthorAndFocus(user1, user2);
        if (focus1 == null) {
            System.out.println("我没有关注她呀！");
        } else {
            System.out.println("我以前关注了她");
            focusService.deleteFocus(focus);
            System.out.println("我刚刚取消了对她的关注");
        }
        return "关注";
    }

    //处理管理员和用户取消关注的请求
    @RequestMapping(value = "/quitFocus2/{name}")
    public String quitfocus2(@PathVariable("name") String user2,
                             Model model, HttpSession session) {
        //首先进行准备关注操作，把登录用户与被关注用户封装成focus对象
        String user1 = (String) session.getAttribute("author");
        Focus focus = new Focus();
        focus.setUser1(user1);
        focus.setUser2(user2);
        System.out.println("我以前关注了她");
        focusService.deleteFocus(focus);
        System.out.println("我刚刚取消了对她的关注");
        //取出当前登陆这个用户的用户名并根据用户名查询其关注的用户
        List<Focus> list = focusService.listFocusByAuthor(user1);
        model.addAttribute("focuss", list);
        return "myFocusUserList";
    }

    //处理管理员和用户查看我的关注者的请求
    @RequestMapping(value = "/myFocus")
    public String myfocus(Model model, HttpSession session) {
        //取出当前登陆这个用户的用户名并根据用户名查询其关注的用户
        String user1 = (String) session.getAttribute("author");
        List<Focus> list = focusService.listFocusByAuthor(user1);
        model.addAttribute("focuss", list);
        return "myFocusUserList";
    }

    //处理管理员和普通用户查看关注者文章列表的请求
    @RequestMapping("/lookUserBlogList/{name}")
    public String lookUserBlogList(@PathVariable("name") String name, Model model) {
        //根据被关注用户的名字查出他的所有文章
        List<Blog> blogs = blogService.listBlogByAuthor(name);
        model.addAttribute("blogs", blogs);
        //跳转到展示文章页面
        return "UserUsersBlogList";
    }

    //处理管理员和普通用户浏览关注者的文章请求
    @RequestMapping(value = "/blogLook/{bid}")
    public String lookBlog(@PathVariable("bid") int bid, Model model, HttpSession session) {
        String user11 = (String) session.getAttribute("author");
        Blog blog = blogService.LookBlogById(bid);
        model.addAttribute("blog", blog);
        model.addAttribute("user11",user11);
        return "blogLook";
    }

}

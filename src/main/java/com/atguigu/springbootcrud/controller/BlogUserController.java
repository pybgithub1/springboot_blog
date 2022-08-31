package com.atguigu.springbootcrud.controller;


import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.User;
import com.atguigu.springbootcrud.service.BlogService;
import com.atguigu.springbootcrud.service.LoginService;
import com.atguigu.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/blogUser")
public class BlogUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private LoginService loginService;


    //处理管理员的查看用户列表请求
    @RequestMapping("/allUserList")
    public String listUser(Model model) {
        //查出用户列表数据
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        //跳转到展示用户列表页面
        return "allUserList";
    }

    //处理管理员查看某个用户文章列表的请求
    @RequestMapping("/lookUserBlogList/{name}")
    public String lookUserBlogList(@PathVariable("name") String name, Model model) {
        //根据作者的名字查出他的所有文章
        List<Blog> blogs = blogService.listBlogByAuthor(name);
        model.addAttribute("blogs", blogs);
        //跳转到展示文章页面
        return "adminUsersBlogList";
    }

    //处理管理员禁言某个用户的请求
    @RequestMapping("/silencedUser/{name}")
    public String silencedUser(@PathVariable("name") String name, Model model) {
        //处理禁言逻辑，把作者名字写入表中
        User user = userService.findByName(name);
        if (user == null) {
            User user1 = loginService.findByUserName(name);
            Boolean save = userService.save(user1);
            System.out.println("禁言成功！");
        } else {
            System.out.println("该用户已经被禁言了！");
        }
        //查出用户列表数据
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        //跳转到展示用户列表页面
        return "allUserList";
    }

    //处理管理员移除某个禁言用户的请求
    @RequestMapping("/RemoveSilencedUser/{name}")
    public String RemoveSilencedUser(@PathVariable("name") String name, Model model) {
        //处理解除禁言逻辑，把用户名字从禁言表中移除
        User user1 = userService.findByName(name);
        if (user1 == null) {
            System.out.println("该用户没有被禁言！");
        } else {
            userService.delete(user1);
            System.out.println("该用户从禁言表中移除成功！");
        }
        //查出用户列表数据
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        //跳转到展示用户列表页面
        return "allUserList";
    }

    //处理管理员查看用户的文章请求
    @RequestMapping(value = "/blogLook/{bid}")
    public String lookBlog(@PathVariable("bid") int bid, Model model, HttpSession session) {
        String user11 = (String) session.getAttribute("author");
        Blog blog = blogService.LookBlogById(bid);
        model.addAttribute("blog", blog);
        model.addAttribute("user11",user11);
        return "blogLook";
    }


    //处理管理员删除用户文章请求
    @RequestMapping(value = "/blogDelete/{bid}", method = RequestMethod.GET)
    public String deleteBlog(@PathVariable("bid") int bid, Model model, HttpSession session) {
        //根据文章的id查出文章，然后取出字段author,删除文章之后，根据文章的author字段确认之后要跳转到谁的文章列表
        Blog blog = blogService.LookBlogById(bid);
        String author = blog.getAuthor();
        blogService.deleteBlog(bid);
        //删除操作完成之后进行查询操作，查出文章列表，在跳转到文章列表页面之后展示文章列表
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        model.addAttribute("blogs", blogs);
        return "adminUsersBlogList";
    }


}

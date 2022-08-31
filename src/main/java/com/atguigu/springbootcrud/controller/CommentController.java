package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.domain.CommentNum;
import com.atguigu.springbootcrud.domain.Zan;
import com.atguigu.springbootcrud.service.BlogService;
import com.atguigu.springbootcrud.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;


    //处理管理员和用户点击想要进行评论的请求
    @RequestMapping(value = "/comment/{bid}")
    public String comment(@PathVariable("bid") int bid,
                          Model model, HttpSession session) {
        String user11 = (String) session.getAttribute("author");
        System.out.println(user11);
        Blog blog = blogService.LookBlogById(bid);
        model.addAttribute("blog", blog);
        model.addAttribute("user11",user11);
        return "blogLook";
    }

    //处理管理员和用户点击发表评论的请求
    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public List<Comment> publish(@RequestParam("comment") String content,
                           @RequestParam("bid") int bid, HttpSession session) {
        String user = (String) session.getAttribute("author");
        blogService.insertCommentByBid(bid, user, content);
        List<Comment> comments = blogService.listCommentByBid(bid);
        //返回该文章的所有评论
        return comments;
    }

    //处理管理员和用户博客评论列表请求
    @ResponseBody
    @RequestMapping(value = "/commentlist/{bid}")
    public List<Comment> commentlist(@PathVariable("bid") int bid) {
        List<Comment> comments = blogService.listCommentByBid(bid);
        //返回该文章的所有评论
        return comments;


    }

    //处理管理员和用户博客评论的总数目请求
    @ResponseBody
    @RequestMapping(value = "/allcommentnums")
    public Map<Integer, Integer> allcommentnums() {
        Map<Integer, Integer> comments = blogService.listCommentNum();
        return comments;
    }


    //处理管理员和用户点击删除评论的请求
    @ResponseBody
    @RequestMapping(value = "/commentDel/{cid}")
    public String commentDel(@PathVariable("cid") int cid) {
        blogService.delCommentById(cid);
        return "删除成功！";
    }


}

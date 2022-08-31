package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Zan;
import com.atguigu.springbootcrud.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/allblog")
public class AllblogController {

    @Autowired
    private BlogService blogService;


    //处理未登陆者全部博客请求
    @RequestMapping("/blogSelect")
    public String select() {
        return "blogSelect";
    }

    //处理管理员和用户全部博客列表请求
    @RequestMapping("/allBlogList")
    public String list(Model model) {
        List<Blog> blogs = blogService.listBlog();
        List<Blog> blogs1 = blogService.listBlogOrderByBack(10);
        List<Blog> blogs2 = blogService.listBlogOrderByHot(8);
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogs1", blogs1);
        model.addAttribute("blogs2", blogs2);
        //跳转到展示文章页面
        return "allBlogList";

    }

    //处理管理员和用户搜索博客的请求
    @RequestMapping(value = "/keyword", method = RequestMethod.POST)
    public String search(@RequestParam("keyword") String keyword,
                         Model model) {
        List<Blog> blogs1 = blogService.listBlogOrderByBack(10);
        List<Blog> blogs2 = blogService.listBlogOrderByHot(6);
        List<Blog> blogs = blogService.listBlogByLikeQuery(keyword);
        model.addAttribute("blogs", blogs);
        model.addAttribute("blogs1", blogs1);
        model.addAttribute("blogs2", blogs2);
        //跳转到展示文章页面
        return "allBlogList";

    }

    //处理管理员和用户博客点赞列表请求
    @ResponseBody
    @RequestMapping(value = "/allzanlist")
    public int[] allzanlist() {
        List<Zan> zans = blogService.showZanNums();
        int[] arr = new int[zans.size()];
        for (int i = 0; i < zans.size(); i++) {
            arr[i] = zans.get(i).getNum();
        }
        return arr;
    }


    //处理管理员和用户查看文章请求
    @RequestMapping(value = "/allBlogLook/{bid}")
    public String look(@PathVariable("bid") int bid, Model model, HttpSession session) {
        String user11 = (String) session.getAttribute("author");
        System.out.println(user11);
        Blog blog = blogService.LookBlogById(bid);
        model.addAttribute("blog", blog);
        model.addAttribute("user11",user11);
        return "blogLook";
    }


}

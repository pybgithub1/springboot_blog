package com.atguigu.springbootcrud.controller;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/myblog")
public class MyblogController {

    @Autowired
    private BlogService blogService;


    //处理未登陆者我的博客导航索引请求
    @RequestMapping("/blogSelect")
    public String select() {
        return "blogSelect";
    }

    //处理管理员和用户我的博客导航索引请求
    @RequestMapping("/blogIndex")
    public String Index() {
        return "blogIndex";
    }


    //处理管理员和用户的个人博客列表请求
    @RequestMapping("/blogList")
    public String list(Model model, HttpSession session) {
        String author = (String) session.getAttribute("author");
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        model.addAttribute("blogs", blogs);
        //跳转到展示文章页面
        return "blogList";
    }

    //处理管理员和用户的添加文章请求，首先跳转到添加文章页面
    @RequestMapping("/blogSaveUi")
    public String saveUi() {
        //跳转到添加文章页面
        return "blogSave";
    }

    //处理管理员和用户的添加文章请求，进行相应的添加文章逻辑处理操作
    @RequestMapping(value = "/blogSave", method = RequestMethod.POST)
    public String save(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("content") String content,
                            Model model, HttpSession session) {
        String author = (String) session.getAttribute("author");
        //保存刚刚新添加的文章内容到数据库
        Blog blog = new Blog();
        blog.setAuthor(author);
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setContent(content);
        Boolean insertBlog = blogService.insertBlog(blog);
        //设置刚刚添加文章的点赞数目为0
        Integer bid = blogService.listBlogIdByAuthorAndTitle(author, title);
        blogService.insertZan(bid,0);
        //插入操作完成之后再一次进行查询操作，查出文章列表，在跳转到文章列表页面后进行展示文章列表
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        model.addAttribute("blogs", blogs);
        return "blogList";
    }

    //处理管理员和用户的查看文章请求并且可以进行更新操作
    @RequestMapping(value = "/blogLook/{bid}")
    public String look(@PathVariable("bid") int bid, Model model) {
        Blog blog = blogService.LookBlogById(bid);
        model.addAttribute("blog", blog);
        return "blogLookAndEdit";
    }


    //处理管理员和用户的编辑文章请求，进行相应的编辑文章逻辑处理操作
    @RequestMapping(value = "/blogEdit", method = RequestMethod.POST)
    public String edit(@RequestParam("bid") int bid,
                       @RequestParam("title") String title,
                       @RequestParam("author") String author,
                       @RequestParam("description") String description,
                       @RequestParam("content") String content, Model model) {
        //更新刚刚新修改的文章内容到数据库
        Blog blog = new Blog();
        blog.setBid(bid);
        blog.setAuthor(author);
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setContent(content);
        blogService.editBlog(blog);
        //更新操作完成之后进行查询操作，查出文章列表，在跳转到文章列表页面之后展示文章列表
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        model.addAttribute("blogs", blogs);
        return "blogList";
    }

    //处理管理员和用户的删除文章请求
    @RequestMapping(value = "/blogDelete/{bid}", method = RequestMethod.GET)
    public String delete(@PathVariable("bid") int bid, Model model, HttpSession session) {
        String author = (String) session.getAttribute("author");
        Boolean deleteBlog = blogService.deleteBlog(bid);
        //删除对应bid的所有评论
        List<Comment> comments = blogService.listCommentByBid(bid);
        for (int i = 0; i < comments.size(); i++) {
            blogService.delCommentById(comments.get(i).getId());
        }
        //删除对应bid的点赞数目
        blogService.delZan(bid);
        //删除操作完成之后进行查询操作，查出文章列表，在跳转到文章列表页面之后展示文章列表
        List<Blog> blogs = blogService.listBlogByAuthor(author);
        model.addAttribute("blogs", blogs);
        return "blogList";
    }

}

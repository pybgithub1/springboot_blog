package com.atguigu.springbootcrud.service.impl;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.domain.CommentNum;
import com.atguigu.springbootcrud.domain.Zan;
import com.atguigu.springbootcrud.mapper.BlogMapper;
import com.atguigu.springbootcrud.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public Boolean insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog.getAuthor(), blog.getTitle(), blog.getDescription(), blog.getContent());
    }

    @Override
    public Boolean deleteBlog(int bid) {
        return blogMapper.deleteBlog(bid);
    }

    @Override
    public Blog LookBlogById(int bid) {
        return blogMapper.lookBlogById(bid);
    }

    @Override
    public Boolean editBlog(Blog blog) {
        return blogMapper.editBlog(blog.getBid(), blog.getAuthor(), blog.getTitle(), blog.getDescription(), blog.getContent());
    }

    @Override
    public List<Blog> listBlogByAuthor(String author) {
        return blogMapper.listBlogByAuthor(author);
    }

    @Override
    public List<Blog> listBlog() {
        return blogMapper.listBlog();
    }

    @Override
    public List<Blog> listBlogOrderByBack(int n) {
        return blogMapper.listBlogOrderByBack(n);
    }

    @Override
    public List<Blog> listBlogOrderByHot(int n) {
        List<Blog> blogs = new ArrayList<>();
        List<Zan> zans = blogMapper.listZanOrderByHot(n);
        for (int i = 0; i < zans.size(); i++) {
            int bid = zans.get(i).getBid();
            Blog blog = blogMapper.lookBlogById(bid);
            blogs.add(i,blog);
        }
        return blogs;
    }

    @Override
    public List<Blog> listBlogByLikeQuery(String keyword) {
        return blogMapper.listBlogByLikeQuery(keyword);
    }


    //接下来是给文章点赞操作相关内容

    //根据博客的作者和标题查询出该博客的bid
    @Override
    public Integer listBlogIdByAuthorAndTitle(String author, String title) {
        return blogMapper.listBlogIdByAuthorAndTitle(author, title);
    }

    //给新添加的文章添加0个赞
    @Override
    public Boolean insertZan(int bid, int number) {
        return blogMapper.insertZan(bid, number);
    }

    //查询出文章的点赞，返回值为Zan
    @Override
    public Zan showZanNum(int bid) {
        return blogMapper.showNum(bid);
    }

    //更新文章的点赞数目
    @Override
    public Boolean updateZanNum(int bid, int number) {
        return blogMapper.updateZanNum(bid, number);
    }

    @Override
    public List<Zan> showZanNums() {
        return blogMapper.showZanNums();
    }

    //根据文章的bid删除对应的点赞
    @Override
    public Boolean delZan(int bid) {
        return blogMapper.delZan(bid);
    }

    @Override
    public Boolean insertCommentByBid(int bid, String user, String content) {
        return blogMapper.insertCommentByBid(bid, user, content);
    }


    @Override
    public List<Comment> listCommentByBid(int bid) {
        return blogMapper.listCommentByBid(bid);
    }

    @Override
    public Map<Integer, Integer> listCommentNum() {
        int[] arr = new int[100];
        List<Comment> comments = blogMapper.listComment();
        for (int i = 0; i < comments.size(); i++) {
            int bid = comments.get(i).getBId();
            arr[i] = bid;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int val = map.get(arr[i]);
                map.put(arr[i], val + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        System.out.println(map);
        return map;
    }

    @Override
    public Boolean delCommentById(int cid) {
        return blogMapper.delCommentById(cid);
    }

    /*@Override
    public int findIdByBidUserContent(int bid, String user, String content) {
        return blogMapper.findIdByBidUserContent(bid,user,content);
    }*/

    @Override
    public int findIdByBidUserContent(int bid, String user, String content) {
        List<Comment> list = blogMapper.findIdByBidUserContent(bid, user, content);
        Comment comment = list.get(list.size()-1);
        return comment.getId();
    }
}

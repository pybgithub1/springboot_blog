package com.atguigu.springbootcrud.service;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.domain.CommentNum;
import com.atguigu.springbootcrud.domain.Zan;

import java.util.List;
import java.util.Map;

public interface BlogService {


    Boolean insertBlog(Blog blog);

    Boolean deleteBlog(int bid);

    Blog LookBlogById(int bid);

    Boolean editBlog(Blog blog);

    List<Blog> listBlogByAuthor(String author);

    List<Blog> listBlog();

    List<Blog> listBlogOrderByBack(int n);

    List<Blog> listBlogOrderByHot(int n);

    List<Blog> listBlogByLikeQuery(String keyword);

    //接下来是给文章点赞操作相关内容
    //根据博客的作者和标题查询出该博客的bid
    Integer listBlogIdByAuthorAndTitle(String author, String title);

    //给新添加的文章添加0个赞
    Boolean insertZan(int bid, int number);

    //根据博客的bid查询出文章的点赞，返回值为Zan
    Zan showZanNum(int bid);

    //更新文章的点赞数目
    Boolean updateZanNum(int bid,int number);

    //列出所有的点赞数目
    List<Zan> showZanNums();

    //根据文章的bid删除对应的点赞
    Boolean delZan(int bid);

    //根据文章的bid插入评论内容
    Boolean insertCommentByBid(int bid,String user,String content);



    //根据文章的bid列出所有的评论
    List<Comment> listCommentByBid(int bid);

    //列出评论的总数目
    Map<Integer, Integer> listCommentNum();

    //根据评论的cid删除该评论
    Boolean delCommentById(int cid);

    /*//根据文章的bid和发表评论用户以及评论内容查找该评论的cid
    int findIdByBidUserContent(int bid, String user, String content);*/

    //根据文章的bid和发表评论用户以及评论内容查找该评论的cid
   int findIdByBidUserContent(int bid, String user, String content);
}

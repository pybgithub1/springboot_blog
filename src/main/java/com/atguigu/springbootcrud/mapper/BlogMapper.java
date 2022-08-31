package com.atguigu.springbootcrud.mapper;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.domain.Zan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Insert("insert into blog (author,title,description,content) values (#{author},#{title},#{description},#{content})")
    Boolean insertBlog(String author, String title, String description, String content);

    @Delete("delete from blog where bid=#{bid}")
    Boolean deleteBlog(int bid);

    @Select("select * from blog where bid=#{bid}")
    Blog lookBlogById(int bid);

    @Update("update blog set author=#{author},title=#{title},description=#{description},content=#{content} where bid=#{bid}")
    Boolean editBlog(int bid, String author, String title, String description, String content);

    @Select("select * from blog where author=#{author}")
    List<Blog> listBlogByAuthor(String author);

    @Select("select * from blog")
    List<Blog> listBlog();

    @Select("select * from blog order by bid desc limit 0,#{n}")
    List<Blog> listBlogOrderByBack(int n);

    @Select("select * from zan order by number desc limit 0,#{n}")
    List<Zan> listZanOrderByHot(int n);

    @Select("select * from blog where title like concat('%',#{keyword},'%')")
    List<Blog> listBlogByLikeQuery(String keyword);

    //接下来是给文章点赞操作相关内容

    //根据博客的作者和标题查询出该博客的bid
    @Select("select bid from blog where author=#{author} and title=#{title}")
    Integer listBlogIdByAuthorAndTitle(String author, String title);

    //给新添加的文章添加0个赞
    @Insert("insert into zan (bid,number) values (#{bid},#{number})")
    Boolean insertZan(int bid, int number);

    //根据文章bid查询出文章的点赞，返回值为Zan
    @Select("select * from zan where bid=#{bid}")
    Zan showNum(int bid);

    //更新文章的点赞数目
    @Update("update zan set number=#{number} where bid=#{bid}")
    Boolean updateZanNum(int bid, int number);

    //查询出所有文章的点赞数目
    @Select("select * from zan")
    List<Zan> showZanNums();

    //根据文章的bid删除对应的点赞
    @Delete("delete from zan where bid=#{bid}")
    Boolean delZan(int bid);

    @Insert("insert into comment (bid,user,content) values (#{bid},#{user},#{content})")
    Boolean insertCommentByBid(int bid, String user, String content);

    @Select("select * from comment where bid=#{bid}")
    List<Comment> listCommentByBid(int bid);

    @Select("select * from comment")
    List<Comment> listComment();

    @Delete("delete from comment where id=#{cid}")
    Boolean delCommentById(int cid);

    @Select("select * from comment where bid=#{bid} and user=#{user} and content=#{content}")
    List<Comment> findIdByBidUserContent(int bid, String user, String content);


  /*  @Select("select id from comment where bid=#{bid} and user=#{user} and content=#{content}")
    int findIdByBidUserContent(int bid, String user, String content);*/
}

package com.atguigu.springbootcrud;

import com.atguigu.springbootcrud.domain.Blog;
import com.atguigu.springbootcrud.domain.Comment;
import com.atguigu.springbootcrud.domain.Msg;
import com.atguigu.springbootcrud.domain.Zan;
import com.atguigu.springbootcrud.mapper.BlogMapper;
import com.atguigu.springbootcrud.service.BlogService;
import com.atguigu.springbootcrud.service.MsgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest
class SpringbootCrudApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogService blogService;

    @Autowired
    private MsgService msgService;


    @Test
    public void test() {
        Zan num = blogMapper.showNum(1);
        System.out.println(num);
    }

    @Test
    public void test2() {
        Blog num = blogMapper.lookBlogById(2);
        System.out.println(num);
    }

    @Test
    public void test3() {
        Integer num = blogMapper.listBlogIdByAuthorAndTitle("13656641499", "几大问题的解决");
        System.out.println(num);
    }

    @Test
    public void test4() {
        Boolean b = blogMapper.insertZan(1000, 0);
        System.out.println(b);
    }

    @Test
    public void test5() {
        List<Blog> blogs = blogMapper.listBlog();
        for (int i = 0; i < blogs.size(); i++) {
            Blog blog = blogs.get(i);
            int bid = blog.getBid();
            System.out.println(bid);
            blogMapper.insertZan(bid, 0);
        }
    }

    @Test
    public void test6() {
        List<Blog> blogs = blogMapper.listBlogByLikeQuery("关注");
        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i).getDescription());
        }
    }

    @Test
    public void test7() {
        List<Blog> blogs = blogService.listBlogOrderByBack(8);
        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i).getBid());
        }
    }

    @Test
    public void test8() {
        List<Zan> zans = blogMapper.listZanOrderByHot(2);
        System.out.println(zans);
        for (int i = 0; i < zans.size(); i++) {
            System.out.println(zans.get(i).getBid());
        }
    }

    @Test
    public void test12() {
        List<Blog> blogs = blogService.listBlogOrderByHot(2);
        System.out.println(blogs);
        /*for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i).getBid()+blogs.get(i).getTitle());
        }*/
    }

    @Test
    public void test9() {
        List<Comment> comments = blogService.listCommentByBid(1);
        for (int i = 0; i < comments.size(); i++) {
            System.out.println(comments.get(i).getContent());
        }
    }

    @Test
    public void test10() {
        List<Comment> list = blogMapper.findIdByBidUserContent(23, "13656641499", "66666");
        msgService.findMsgByFromuserAndTouser("", "");
        System.out.println(list);

    }

    @Test
    public void test11() {
        int i = blogService.findIdByBidUserContent(31, "13656641499", "66666");
        System.out.println(i);

    }

    @Test
    public void test13() {
        List<Msg> pyb = msgService.findMsgsGunDongBack("xfy", "13656641499", "2");
    }

    @Test
    public void test14() throws InterruptedException {
        for (int i = 1; i < 20; i=i+2) {
            String s1 = Integer.toString(i);
            Date date = new Date();//获取当前的日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(date);//获取String类型的时间
            msgService.insertMsgByFromuserAndTouserAndMsg("13656641499", "xfy", s1, time);
            Thread.sleep(1000);
            String s2 = Integer.toString(i+1);
            msgService.insertMsgByFromuserAndTouserAndMsg("xfy", "13656641499", s2, time);


        }
    }
}

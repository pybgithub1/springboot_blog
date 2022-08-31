package com.atguigu.springbootcrud.config;

import com.atguigu.springbootcrud.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/").excludePathPatterns("/blogMain.html")
                .excludePathPatterns("/login/loginAdminIn").excludePathPatterns("/login/loginUserIn")
                .excludePathPatterns("/login/loginAdminUi").excludePathPatterns("/login/loginUserUi")
                .excludePathPatterns("/register/registerIn").excludePathPatterns("/register/registerUi")
                .excludePathPatterns("/verify/getVerifyCodeAdmin").excludePathPatterns("/verify/getVerifyCodeUser")
                .excludePathPatterns("/allblog/blogSelect").excludePathPatterns("/myblog/blogSelect")
                .excludePathPatterns("/blogmsg/blogSelect").excludePathPatterns("/myfocus/blogSelect")
                .excludePathPatterns("/test");
    }*/
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("blogMain");
                registry.addViewController("/blogMain.html").setViewName("blogMain");
                registry.addViewController("/test").setViewName("chat");
                registry.addViewController("/adminLogin.html").setViewName("adminLogin");
                registry.addViewController("/userLogin.html").setViewName("userLogin");

            }

            /*//注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/userMain.html", "/", "/admin/index");
            }*/
        };

        return adapter;
    }


    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(
                "classpath:/static/");
    }*/
}
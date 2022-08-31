package com.atguigu.springbootcrud.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        HttpSession session = request.getSession();
        Object admin =  session.getAttribute("author");
        if(admin!=null){
            return true;
        }else {
            request.getRequestDispatcher("/blogMain.html").forward(request, response);
            return false;
        }

    }
}

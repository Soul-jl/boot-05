package com.jy.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        //从session中取出userInfo，判断用户是否登陆
        HttpSession session = httpServletRequest.getSession();
        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute("userInfo");
        if (null != userInfo && userInfo.get("code").toString().equals("1")) {
            //已登录用户
            flag = true;
        } else {
            //未登录
            //重定向用户到登陆页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/toLogin");
        }
        return flag;
    }

}

package com.jy.controller.user;

import com.jy.model.user.User;
import com.jy.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    String insertUser(User user) {
        userService.insertUser(user);
        return "index";
    }


    @RequestMapping("toLogin")
    String toLoginPage() {
        return "login";
    }

    @RequestMapping("login")
    @ResponseBody
    Map<String, Object> login(HttpServletRequest request,User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code",2); //2代表验证码错误
        HttpSession session = request.getSession();
        //判断验证码，验证码正确才可以查询数据库
        Object imgcode = session.getAttribute("imageCode");
        try {
            if (null != imgcode) {
                if (imgcode.toString().equals(user.getUserImgCode())) {
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserAccount(), user.getUserPwd());
                    //执行登陆
                    subject.login(token);
                    //获取登录结果
                    User u = (User) subject.getPrincipals().getPrimaryPrincipal();
                    if (null != u) {
                        //登陆成功
                        map.put("code", 1);
                        //向session中放一份用户信息
                        session.setAttribute("userInfo", u);
                    }
                }
            }
        } catch (AuthenticationException authenticationException) {
            map.put("code", 0);
        } finally {
            return map;
        }
    }


}

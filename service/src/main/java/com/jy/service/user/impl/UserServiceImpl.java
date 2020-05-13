package com.jy.service.user.impl;

import com.jy.mapper.user.UserMapper;
import com.jy.model.user.User;
import com.jy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        //code默认0，代表用户名或密码错误
        int code = 0;
        User u = userMapper.login(user);
        if (null != u) {
            //登陆成功
            code = 1;
        }
        map.put("userInfo", u);
        map.put("code", code);
        return map;
    }
}

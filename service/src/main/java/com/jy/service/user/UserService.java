package com.jy.service.user;


import com.jy.model.user.User;

import java.util.Map;

public interface UserService {
    void insertUser(User user);

    Map<String,Object> login(User user);

}

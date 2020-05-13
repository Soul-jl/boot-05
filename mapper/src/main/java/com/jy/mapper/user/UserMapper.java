package com.jy.mapper.user;

import com.jy.model.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into t_USERS (t_account,t_pwd) values (#{userAccount},#{userPwd})")
    void insertUser(User user);

    @Select("select t_id userID, t_account userAccount, t_pwd userPwd" +
            " from t_users" +
            " where t_account = #{userAccount}" +
            " and t_pwd = #{userPwd}")
    User login(User user);
}

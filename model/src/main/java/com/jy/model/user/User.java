package com.jy.model.user;

import com.jy.common.PageUtil;

import java.io.Serializable;

public class User extends PageUtil implements Serializable {

    private int userID;

    private String userAccount;

    private String userPwd;

    private String userImgCode;

    public String getUserImgCode() {
        return userImgCode;
    }

    public void setUserImgCode(String userImgCode) {
        this.userImgCode = userImgCode;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}

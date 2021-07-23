package com.onen.userinfo.controller;

import com.onen.userinfo.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;


public interface UserController {
    Object login(UserInfo user, HttpServletRequest req);
    Object getUsers();
    Object delUser(int id);
}

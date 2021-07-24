package com.onen.userinfo.controller;

import com.onen.userinfo.pojo.SearchInfo;
import com.onen.userinfo.pojo.UserInfo;
import com.onen.userinfo.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;


public interface UserController {
    ResponseUtil login(UserInfo user, HttpServletRequest req);
    ResponseUtil getUsersByPage(SearchInfo search,HttpServletRequest req);
    Object delUser(int id);
    ResponseUtil isAdmin(HttpServletRequest req);
    ResponseUtil addUser(UserInfo userInfo,HttpServletRequest req);
    ResponseUtil getUserById(int id);
    ResponseUtil updateUser(UserInfo userInfo);

}

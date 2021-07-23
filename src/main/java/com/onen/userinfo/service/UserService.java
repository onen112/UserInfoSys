package com.onen.userinfo.service;

import com.onen.userinfo.pojo.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo login(String username , String password);

    List<UserInfo> getUsers();

    int delUser(int id);
}

package com.onen.userinfo.service;

import com.onen.userinfo.pojo.SearchInfo;
import com.onen.userinfo.pojo.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo login(String username , String password);

    List<UserInfo> getUsers(SearchInfo searchInfo);

    int delUser(int id);

    int addUser(UserInfo userInfo);
    int getUsername(String username);

    UserInfo getUserById(int id);

    int update(UserInfo userInfo);
    int getCount(SearchInfo searchInfo);
}

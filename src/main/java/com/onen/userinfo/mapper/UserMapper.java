package com.onen.userinfo.mapper;

import com.onen.userinfo.pojo.SearchInfo;
import com.onen.userinfo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserInfo login(String username, String password);

    UserInfo getUserById(int id);


    List<UserInfo> getUsers(SearchInfo searchInfo);

    int delUser(int id);

    int addUser(UserInfo userInfo);

    int getUsername(String username);

    int update(UserInfo userInfo);

    int getCount(SearchInfo searchInfo);

}

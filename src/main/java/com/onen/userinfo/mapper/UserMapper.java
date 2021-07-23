package com.onen.userinfo.mapper;

import com.onen.userinfo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserInfo login(String username, String password);

    UserInfo getUserById(int id);


    List<UserInfo> getUsers();

    int delUser(int id);
}

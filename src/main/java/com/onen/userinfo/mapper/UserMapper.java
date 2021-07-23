package com.onen.userinfo.mapper;

import com.onen.userinfo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<UserInfo> login(String username, String password);

    public List<UserInfo> test();



}

package com.onen.userinfo.service.serviceImpl;

import com.onen.userinfo.mapper.UserMapper;
import com.onen.userinfo.pojo.SearchInfo;
import com.onen.userinfo.pojo.UserInfo;
import com.onen.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    UserMapper userMapper;

    @Override
    public UserInfo login(String username, String password) {

        return userMapper.login(username,password);
    }

    @Override
    public List<UserInfo> getUsers(SearchInfo searchInfo) {
        return userMapper.getUsers(searchInfo);
    }

    @Override
    public int delUser(int id) {
        return userMapper.delUser(id);
    }


    @Override
    public int addUser(UserInfo userInfo) {
        userMapper.addUser(userInfo);
        return 1;
    }

    @Override
    public int getUsername(String username) {
        return userMapper.getUsername(username);
    }

    @Override
    public UserInfo getUserById(int id) {

        return userMapper.getUserById(id);
    }

    @Override
    public int update(UserInfo userInfo) {
        return userMapper.update(userInfo);
    }

    @Override
    public int getCount(SearchInfo searchInfo) {
        return userMapper.getCount(searchInfo);
    }
}

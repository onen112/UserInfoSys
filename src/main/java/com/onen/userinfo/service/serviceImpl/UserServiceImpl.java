package com.onen.userinfo.service.serviceImpl;

import com.onen.userinfo.mapper.UserMapper;
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
    public List<UserInfo> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public int delUser(int id) {
        return userMapper.delUser(id);
    }
}

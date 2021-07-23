package com.onen.userinfo.service.serviceImpl;

import com.onen.userinfo.mapper.UserMapper;
import com.onen.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {

        return 1>  0;
    }
}

package com.onen.userinfo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void login() {
        System.out.println(userMapper);
    }


    @Test
    void getUsers() {
        System.out.println(userMapper.getUsers());
    }
}
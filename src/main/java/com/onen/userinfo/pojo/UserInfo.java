package com.onen.userinfo.pojo;

import lombok.Data;

@Data
public class UserInfo {
    private int id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private String address;
    private int age;
    private String qq;
    private String email;
    private int isadmin;
}

package com.onen.userinfo.controller.controllerImpl;

import com.onen.userinfo.controller.UserController;
import com.onen.userinfo.pojo.UserInfo;
import com.onen.userinfo.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
    /**
     * password: "123123123"
     * username: "123"
     * @return
     */
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/login")
    @Override
    public Object login(@RequestBody UserInfo user) {
        String msg = "未知错误";
        int state = -1;
        if(user.getPassword() != null && ! user.getPassword().trim().equals("") && user.getUsername() != null && !user.getUsername().trim().equals("")){
            if(userService.login(user.getUsername(),user.getPassword())){
                state = 1;
                msg = "登录成功";
            }else{
                state = 0;
                msg = "用户名或密码错误请重试！";
            }
        }else{
            msg = "用户名或密码不能为空，请重试！";
        }
        Map<String,Object> ret = new HashMap<>();
        ret.put("state",state);
        ret.put("msg",msg);
        return ret;
    }
}

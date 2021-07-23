package com.onen.userinfo.controller.controllerImpl;

import com.onen.userinfo.controller.UserController;
import com.onen.userinfo.pojo.UserInfo;
import com.onen.userinfo.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    public Object login(@RequestBody UserInfo user, HttpServletRequest req) {
        String msg = "未知错误";
        int state = -1;
        if(user.getPassword() != null && ! user.getPassword().trim().equals("") && user.getUsername() != null && !user.getUsername().trim().equals("")){
            UserInfo userInfo = userService.login(user.getUsername(),user.getPassword());
            if(userInfo != null){
                HttpSession session = req.getSession();
                session.setAttribute("user",userInfo);
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

    @RequestMapping("/getUsers")
    @Override
    public Object getUsers() {
        int state = 1;
        List<UserInfo> users;
        String msg = "查询成功！";
        users = userService.getUsers();
        Map<String, Object> ret = new HashMap<>();

        ret.put("state",state);
        ret.put("users",users);
        ret.put("msg",msg);
        return ret;
    }

    @RequestMapping("/del")
    @Override
    public Object delUser(int id){
        int state = -1;
        String msg = "未知错误";
        if(userService.delUser(id) > 0){
            state = 1;
            msg = "删除成功！";
        }else{
            state = 0;
            msg = "删除失败！";
        }
        Map<String , Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        return map;
    }
}

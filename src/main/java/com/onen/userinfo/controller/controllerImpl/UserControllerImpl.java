package com.onen.userinfo.controller.controllerImpl;

import com.onen.userinfo.controller.UserController;
import com.onen.userinfo.pojo.SearchInfo;
import com.onen.userinfo.pojo.UserInfo;
import com.onen.userinfo.service.serviceImpl.UserServiceImpl;
import com.onen.userinfo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseUtil login(@RequestBody UserInfo user, HttpServletRequest req) {
        String msg = "未知错误";
        int state = -1;
        ResponseUtil<Object> ret = new ResponseUtil<>();
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
        ret.setData(null);
        ret.setMsg(msg);
        ret.setState(state);
        return ret;
    }

    @RequestMapping("/getUsers")
    @Override
    public ResponseUtil getUsersByPage(SearchInfo searchInfo,HttpServletRequest req) {
        int state = -1;
        List<UserInfo> users;
        String msg = "未知错误";
        HttpSession session = req.getSession(false);
        ResponseUtil<Map<String,Object>> ret = new ResponseUtil<>();
        Map<String,Object> map = new HashMap<>();
        if(session != null && session.getAttribute("user") != null){
            UserInfo user =(UserInfo)session.getAttribute("user");
            searchInfo.setIsadmin(user.getIsadmin());
            searchInfo.setSkipCount((searchInfo.getCpage()-1) * searchInfo.getPsize());

            int tcount = userService.getCount(searchInfo);

            int cpage =(int)Math.ceil(tcount/(double)searchInfo.getPsize());
            msg = "查询成功！";
            users = userService.getUsers(searchInfo);

            map.put("tcount",tcount);
            map.put("cpage",cpage);
            map.put("users",users);

        }else{
            state = 0;
            msg = "请先登录";
        }

        ret.setState(state);
        ret.setMsg(msg);
        ret.setData(map);
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

    @RequestMapping("/isAdmin")
    @Override
    public ResponseUtil isAdmin(HttpServletRequest req) {
        ResponseUtil<Boolean> ret = new ResponseUtil<>();
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            UserInfo user = (UserInfo)session.getAttribute("user");
            ret.setMsg("查询成功！");
            ret.setState(1);
            ret.setData(user.getIsadmin() == 1);
        }else{
            ret.setState(0);
            ret.setMsg("查询失败，用户为登录！");
        }
        return ret;
    }

    @RequestMapping("/addUser")
    @Override
    public ResponseUtil addUser(@RequestBody UserInfo userInfo,HttpServletRequest req) {
        int state = -1;
        String msg = "未知错误";
        HttpSession session = req.getSession(false);
        ResponseUtil<Object> ret = new ResponseUtil<>();
        if(session != null && session.getAttribute("user") != null){
            UserInfo user1 = (UserInfo)session.getAttribute("user");
            if(user1.getIsadmin() == 0){
                userInfo.setIsadmin(0);
            }
            if(userService.getUsername(userInfo.getUsername()) > 0){
                msg = "用户名已存在";
            }else{
                int i = userService.addUser(userInfo);
                if(i > 0){
                    state = 1;
                    msg = "添加成功";
                }else{
                    state = 0;
                    msg = "添加失败";
                }
            }
        }else{
            msg = "用户未登录";
        }
        System.out.println(userInfo);
        ret.setMsg(msg);
        ret.setState(state);
        ret.setData(null);
        return ret;
    }

    @RequestMapping("/getUserById")

    @Override
    public ResponseUtil getUserById(int id) {
        ResponseUtil<UserInfo> ret = new ResponseUtil<>();
        UserInfo user = userService.getUserById(id);
        ret.setData(user);
        ret.setMsg("查询成功");
        ret.setState(1);
        return ret;
    }

    @RequestMapping("/update")
    @Override
    public ResponseUtil updateUser(@RequestBody UserInfo userInfo) {
        ResponseUtil<Integer> ret = new ResponseUtil<>();
        ret.setData(userService.update(userInfo));
        System.out.println(userInfo);
        ret.setState(1);
        ret.setMsg("更新成功");
        return ret;
    }


}

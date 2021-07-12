package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.User;
import com.example.blog2.service.UserService;
import com.example.blog2.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/7/6 15:26
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(@RequestHeader Map<String, Object> head, @RequestBody Map<String, Object> para) throws Exception {
        System.out.println(head);
        String username = (String) para.get("username");
        String password = (String) para.get("password");
        User user = userService.checkUser(username, password);
        if (user != null) {
            String token = TokenUtil.getToken(user);
            Map<String, Object> info = new HashMap<>();
            info.put("user", user);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "管理员登录成功", info);
        } else {
            return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
        }
    }
}

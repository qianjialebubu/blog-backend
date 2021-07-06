package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @PostMapping(value = "/login")
    public Result login(@RequestHeader Map<String, Object> head, @RequestBody Map<String, Object> para) throws Exception {
        System.out.println(head);
        System.out.println(para);
        String username = (String) para.get("username");
        String password = (String) para.get("password");
        return new Result(true, StatusCode.OK,"登录成功",username);
    }
}

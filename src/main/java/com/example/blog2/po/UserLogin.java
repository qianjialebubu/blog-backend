package com.example.blog2.po;

import lombok.Data;

/**
 * @program: myblog-backendv1.5
 * @description: 登录接收前端参数
 * @author: qjl
 * @create: 2023-07-31 13:41
 **/
@Data
public class UserLogin extends User{
    private Boolean rememberMe;
}

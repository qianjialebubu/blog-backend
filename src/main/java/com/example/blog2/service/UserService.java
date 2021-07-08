package com.example.blog2.service;

import com.example.blog2.po.User;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/3/31 22:56
 */
public interface UserService {
    User checkUser(String username, String password);
    User findUserById(Long id);

    void updateUser(Long id, User admin);
}

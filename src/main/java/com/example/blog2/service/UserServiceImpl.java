package com.example.blog2.service;

import com.example.blog2.dao.UserRepository;
import com.example.blog2.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/3/31 22:58
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
}

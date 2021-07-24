package com.example.blog2.service;

import com.example.blog2.dao.UserRepository;
import com.example.blog2.po.User;
import com.example.blog2.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        if (user.getAvatar() == null || "".equals(user.getAvatar())){
            user.setAvatar("http://hikari.top/images/d5026d40-d68e-4e0d-a0b9-37cd58e3ff35.png");
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User admin) {
        User u = userRepository.getOne(id);
        BeanUtils.copyProperties(admin, u, MyBeanUtils.getNullPropertyNames(admin));
        return userRepository.save(u);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}

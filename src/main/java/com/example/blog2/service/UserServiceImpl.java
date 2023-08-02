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
            user.setAvatar("http://blog-bu.oss-cn-beijing.aliyuncs.com/ea94074a-8b40-4f98-ab5d-12f1451ce089.jpg?Expires=1680204638&OSSAccessKeyId=LTAI5tCuv6n9NSMSBUs6nQDV&Signature=CF5ikmIM9l3MmBb33tl8bePU8M0%3D");
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

    @Override
    public User selectUserByName(String username) {
       return userRepository.selectUserByName(username);
    }


}

package com.example.blog2.common.shiro;

import com.example.blog2.po.User;
import com.example.blog2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: myblog-backendv1.5
 * @description: 自定义的realm
 * @author: qjl
 * @create: 2023-07-31 10:58
 **/

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }

    /**
     * 登录校验
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取当前用户 用户名和密码
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        // 这里根据传进来的密码进行判断密码是否正确

        String password = userService.selectUserByName(userToken.getUsername()).getPassword();
//        String password = String.valueOf(userToken.getPassword());
        User user = userService.checkUser(userToken.getUsername(), String.valueOf(userToken.getPassword()));
        if(user==null){return null;}
//        Subject subject = SecurityUtils.getSubject();
        // 封装用户登录数据
        // 密码认证
        return new SimpleAuthenticationInfo("",password,"");
//        return null;// 会抛出异常
    }
}

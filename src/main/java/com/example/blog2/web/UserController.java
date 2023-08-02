package com.example.blog2.web;
import com.example.blog2.common.aop.LogAnnotation;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.User;
import com.example.blog2.po.UserLogin;
import com.example.blog2.service.UserService;
import com.example.blog2.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@Api(tags = "用户接口文档")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口，传入username和password，使用jwt进行鉴权和登录的校验,在登录接口处进行日志的记录
     * @param para
     * @return
     */
    @PostMapping(value = "/login")
    @LogAnnotation(module="用户",operator="获取用户登录信息")
    @ApiOperation("用户登录接口")
    public Result login(@RequestBody Map<String, UserLogin> para) {
        UserLogin u = para.get("user");
        Boolean rememberMe = u.getRememberMe();//判断是否是点击了记住我的按钮
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken tokenShiro = new UsernamePasswordToken(u.getUsername(),u.getPassword());
        // 此处根据name和password进行判断该用户是否存在
//        User user = userService.checkUser(u.getUsername(), u.getPassword());
        User user = userService.selectUserByName(u.getUsername());
        try {
            if (user != null) {
                // 执行登录的方法，没有异常就ok
                subject.login(tokenShiro);
                String token = TokenUtil.sign(user);
                Map<String, Object> info = new HashMap<>();
                user.setLoginProvince(u.getLoginProvince());
                user.setLoginCity(u.getLoginCity());
                user.setLoginLat(u.getLoginLat());
                user.setLoginLng(u.getLoginLng());
                user.setLastLoginTime(new Date());
                User newUser = userService.save(user);
                info.put("user", newUser);
                info.put("token", token);
                return new Result(true, StatusCode.OK, "管理员登录成功", info);
            }
        }catch (UnknownAccountException e) {
            return new Result(true, StatusCode.ERROR, "用户名错误", null);
        }
        catch (IncorrectCredentialsException e) {
            return new Result(true, StatusCode.ERROR, "密码错误", null);
        }

        return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
    }
//    @PostMapping(value = "/login")
//    public Result login(@RequestBody Map<String, User> para) {
//        User u = para.get("user");
//        System.out.println(u);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token1 = new UsernamePasswordToken(u.getUsername(),u.getPassword());
//        subject.login(token1);
//        User user = userService.checkUser(u.getUsername(), u.getPassword());
//        System.out.println(user);
//        if (user != null) {
//            String token = TokenUtil.sign(user);
//            Map<String, Object> info = new HashMap<>();
//            user.setLoginProvince(u.getLoginProvince());
//            user.setLoginCity(u.getLoginCity());
//            user.setLoginLat(u.getLoginLat());
//            user.setLoginLng(u.getLoginLng());
//            user.setLastLoginTime(new Date());
//            User newUser = userService.save(user);
//            info.put("user", newUser);
//            info.put("token", token);
//            return new Result(true, StatusCode.OK, "管理员登录成功", info);
//        } else {
//            return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
//        }
//    }
    @LogAnnotation(module="用户",operator="获取用户注册信息")
    @PostMapping(value = "/registor")
    @ApiOperation("用户注册接口")
    public Result post(@RequestBody Map<String, User> para)  {
        User u = para.get("user");
        if (u != null) {
            User user = userService.save(u);
//            System.out.println(user);
            String token = TokenUtil.sign(user);
            Map<String, Object> info = new HashMap<>();
            info.put("user", user);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "管理员登录成功", info);
        } else {
            return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
        }
    }
    @ApiOperation("获取用户列表接口")
//    @LogAnnotation(module="用户",operator="获取用户列表")
    @GetMapping(value = "/users")
    public Result get(){
//        System.out.println(userService.listUser());
        return new Result(true, StatusCode.OK, "获取用户列表成功", userService.listUser());
    }
}

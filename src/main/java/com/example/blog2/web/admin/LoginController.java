package com.example.blog2.web.admin;

import com.example.blog2.common.aop.LogAnnotation;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.User;
import com.example.blog2.service.UserService;
import com.example.blog2.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "管理员登录接口文档")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    @LogAnnotation(module="管理员",operator="获取管理员登录信息")
    public Result login(@RequestHeader Map<String, Object> head, @RequestBody Map<String, Object> para) {
        String username = (String) para.get("username");
        String password = (String) para.get("password");
        User user = userService.checkUser(username, password);
//        System.out.println("管理员登录");
        if (user != null) {
            String token = TokenUtil.sign(user);
            Map<String,Object> info = new HashMap<>();
            info.put("user",user);
            info.put("token",token);
            return new Result(true, StatusCode.OK, "管理员登录成功", info);
        } else {
            return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
        }
    }

    @GetMapping("/logout")
    @ApiOperation("管理员退出")
    @LogAnnotation(module="管理员",operator="获取管理员退出登录信息")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}

package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Type;
import com.example.blog2.po.User;
import com.example.blog2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "后台个人中心接口文档")
public class AdministratorController {

    @Autowired
    private UserService userService;

    @PostMapping("/setAvatar")
    @ApiOperation("用户设置头像接口文档")
    public Result setAvatar(@RequestBody Map<String, Object> para) {
//        System.out.println(para);
        String picUrl = (String) para.get("pic_url");
//        System.out.println(picUrl);
        long id = Long.parseLong(para.get("user_id").toString());
        User admin = userService.findUserById(id);
//        System.out.println(admin);
        if (admin == null){
            return new Result(true, StatusCode.ERROR, "用户不存在", null);
        } else {
            admin.setAvatar(picUrl);
//            System.out.println(admin);
            userService.updateUser(id,admin);
            admin.setPassword(null);
        }
        return new Result(true, StatusCode.OK, "新增成功", admin);
    }

    @PostMapping("/user")
    @ApiOperation("修改用户信息接口文档")
    public Result post(@RequestBody Map<String, User> para) {
        User user = para.get("user");
        User u;
        if (user.getId() == null){
             u = userService.save(user);
        } else {
             u = userService.updateUser(user.getId(),user);
        }
        return new Result(true, StatusCode.OK, "修改用户信息成功",u);
    }

    @GetMapping("/users/{id}/delete")
    @ApiOperation("删除用户信息接口文档")
    public Result delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new Result(true, StatusCode.OK, "删除用户信息成功", null);
    }

}

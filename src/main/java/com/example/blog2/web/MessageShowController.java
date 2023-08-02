package com.example.blog2.web;

import com.example.blog2.po.*;
import com.example.blog2.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@Api(tags = "首页留言列表接口文档")
public class MessageShowController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    @ApiOperation("获取留言列表接口文档")
    public Result messages() {
        return new Result(true, StatusCode.OK, "获取留言列表成功",messageService.listMessage());
    }

    @PostMapping("/messages")
    @ApiOperation("新增留言接口文档")
    public Result post(@RequestBody Map<String, Message> para) {
        System.out.println(para);
        Message message = para.get("message");
        System.out.println(message);
        Message m;
        if (message.getId() == null){
            m = messageService.saveMessage(message);
        } else {
            m = messageService.updateMessage(message.getId(),message);
        }
        if (m == null) {
            return new Result(false,StatusCode.ERROR,"操作失败");
        }
        return new Result(true,StatusCode.OK,"操作成功");
    }

}

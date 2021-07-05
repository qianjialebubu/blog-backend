package com.example.blog2.web;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.blog2.po.Comment;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/4/20 9:58
 */
@Controller
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    private final String avatar = "/images/avatar.png";

    //获取评论集合
    @GetMapping("/comments/{blogId}")
    public Result comments(@PathVariable Long blogId) {
        return new Result(true, StatusCode.OK, "获取博客评论成功", commentService.listCommentByBlogId(blogId));
    }

    @PostMapping("/comments")
    public String post(@RequestParam Comment comment) {
        Long blogId = comment.getBlog().getId();
        System.out.println("-------" + comment);
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}

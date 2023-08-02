package com.example.blog2.web.admin;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台管理首页接口文档")
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminIndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;
    //获取博客数量
    @GetMapping("/getBlogCount")
    @ApiOperation("获取博客总数接口文档")
    public Result getBlogList() {
        return new Result(true, StatusCode.OK, "获取博客总数成功", blogService.countBlog());
    }
    //获取总阅读量
    @GetMapping("/getViewCount")
    @ApiOperation("获取阅读总数接口文档")
    public Result getViewCount() {
        return new Result(true, StatusCode.OK, "获取阅读总数成功", blogService.countViews());
    }

    //获取总点赞数
    @GetMapping("/getAppreciateCount")
    @ApiOperation("获取赞赏总数接口文档")
    public Result getAppreciateCounts() {
        return new Result(true, StatusCode.OK, "获取赞赏总数成功", blogService.countAppreciate());
    }

    //获取总评论数
    @GetMapping("/getCommentCount")
    @ApiOperation("获取评论总数接口文档")
    public Result getCommentCount() {
        return new Result(true, StatusCode.OK, "获取评论总数成功", blogService.countComment());
    }

    //根据月份统计阅读量
    @GetMapping("/getViewCountByMonth")
    @ApiOperation("获取按月份统计阅读总数接口文档")
    public Result getBlogViewsByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计阅读总数成功", blogService.ViewCountByMonth());
    }

    //根据月份统计博客发表数
    @GetMapping("/getBlogCountByMonth")
    @ApiOperation("获取按月份统计发表总数接口文档")
    public Result getBlogCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计发表总数成功", blogService.BlogCountByMonth());
    }

    //根据月份统计评论数
    @GetMapping("/getCommentCountByMonth")
    @ApiOperation("获取按月份统计评论总数接口文档")
    public Result getCommentCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计评论总数成功", commentService.CommentCountByMonth());
    }

    //根据月份统计评论数
    @GetMapping("/getAppreciateCountByMonth")
    @ApiOperation("获取按月份统计评论总数接口文档")
    public Result getAppreciateCountByMonth() {
        return new Result(true, StatusCode.OK, "获取按月份统计评论总数成功", blogService.appreciateCountByMonth());
    }

    @GetMapping("/getFullTagList")
    @ApiOperation("获取所有博客标签成功接口文档")
    public Result getFullTagList() {
        return new Result(true, StatusCode.OK, "获取所有博客标签成功", tagService.listTag());
    }

    @GetMapping("/getFullTypeList")
    @ApiOperation("获取博客全部分类成功接口文档")
    public Result getFullTypeList() {
        return new Result(true, StatusCode.OK, "获取博客全部分类成功",typeService.listType());
    }

    @GetMapping("/getCommentList")
    @ApiOperation("获取评论列表成功接口文档")
    public Result getCommentList() {
        return new Result(true, StatusCode.OK, "获取评论列表成功",commentService.listComment());
    }

    @GetMapping("/getUserAreaList")
    @ApiOperation("获取用户地址列表成功接口文档")
    public Result getuserAreaList(){
        return new Result(true,StatusCode.OK,"获取用户地址列表成功",userService.listUser());
    }
}

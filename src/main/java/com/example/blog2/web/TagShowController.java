package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Api(tags = "首页博客标签接口文档")
public class TagShowController {
    @Autowired
    private TagService tagService;

    @GetMapping("/getTagList")
    @ApiOperation("获取博客标签接口文档")
    public Result getTagList() {
        return new Result(true, StatusCode.OK, "获取博客标签成功", tagService.listTagTop(10));
    }

    @GetMapping("/getFullTagList")
    @ApiOperation("获取所有博客标签接口文档")
    public Result getFullTagList() {
        return new Result(true, StatusCode.OK, "获取所有博客标签成功", tagService.listTag());
    }

}

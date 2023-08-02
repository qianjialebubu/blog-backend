package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
@Api(tags = "首页查询接口文档")
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archiveBlog")
    @ApiOperation("查询博客列表接口文档")
    public Result<Model> archives(){
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.archiveBlog());
    }

    @GetMapping("/countBlog")
    @ApiOperation("查询博客数量接口文档")
    public Result count(){
        return new Result(true, StatusCode.OK, "查询博客列表成功", blogService.countBlog());
    }


}

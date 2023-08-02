package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Api(tags = "首页项目展示接口文档")
public class ProjectShowController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/projects")
    @ApiOperation("获取项目列表接口文档")
    public Result projects() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }
}

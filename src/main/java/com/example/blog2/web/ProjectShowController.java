package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/7/12 21:08
 */

@RestController
@CrossOrigin
public class ProjectShowController {
    @Autowired
    private ProjectService projectService;
    @GetMapping("/projects")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取项目列表成功", projectService.listProject());
    }
}

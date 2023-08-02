package com.example.blog2.web;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: myblog-backendv1.5
 * @description:
 * @author: qjl
 * @create: 2023-07-10 10:28
 **/
@Slf4j
@RestController
@CrossOrigin
@Api(tags = "测试接口文档")
public class PythonController {
    @GetMapping(value = "/python/test")
    @ApiOperation("python测试接口文档")
    public String testPython(){
        return "python端测试";
    }
}

package com.example.blog2.web;

import com.example.blog2.po.Essay;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.EssayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "首页随笔接口文档")
public class EssayShowController {
    @Autowired
    private EssayService essayService;
    @GetMapping("/essays")
    @ApiOperation("获取随笔列表接口文档")
    public Result essays() {
        return new Result(true, StatusCode.OK, "获取随笔列表成功", essayService.listEssay());
    }
}

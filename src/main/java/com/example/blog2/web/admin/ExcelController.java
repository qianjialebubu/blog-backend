package com.example.blog2.web.admin;
import com.alibaba.excel.EasyExcel;
import com.example.blog2.common.aop.LogAnnotation;
import com.example.blog2.po.*;
import com.example.blog2.po.excel.ExcelType;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @program: myblog-backendv1.5
 * @description: 导出excel表格
 * @author: qjl
 * @create: 2023-07-31 13:57
 **/
@Controller
@RequestMapping(value = "/admin/excel")
@CrossOrigin
@Api(tags = "导出数据接口文档")
public class ExcelController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/outTypes")
    @ResponseBody
    @ApiOperation("导出所有的分类数据")
    @LogAnnotation(module="导出数据",operator="导出分类列表的信息")
    public Result outputTypes(@RequestBody Map<String, String> para){
        // 查询出所有的分类数据
        List<Type> listType = typeService.listType();
        String replacedPath = para.get("data").replace("\\", "\\\\")+"\\";
//       String replacedPath = para.get("data").replace("\\", "\\\\")+"\\";
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeStr = timeFormat.format(new Date());
        String excelName = "type-"+ timeStr +".xlsx";
        String filename = replacedPath+excelName;
        Path path = null;
        try {
            path = FileSystems.getDefault().getPath(replacedPath);
        }catch (Exception e){
            return new Result(true, StatusCode.ERROR, "路径错误");
        }
        if (Files.exists(path)) {
            System.out.println("路径已存在！");
        } else {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("无法创建路径：" + e.getMessage());
            }
        }
        // 根据Type模板构建数据
        List<ExcelType> types = new ArrayList<>();
        if(listType == null){
            return new Result(true, StatusCode.ERROR, "路径错误");
        }
        // listType 不为空
        for (Type type : listType) {
            ExcelType excelType = ExcelType.builder()
                    .id(type.getId())
                    .name(type.getName())
                    .color(type.getColor())
                    .pic_url(type.getPic_url())
                    .build();
            types.add(excelType);
        }
        // 向Excel中写入数据 也可以通过 head(Class<?>) 指定数据模板
        EasyExcel.write(filename, ExcelType.class)
                .sheet("用户信息")
                .doWrite(types);
        return new Result(true, StatusCode.OK, "导出分类数据成功");

    }
}

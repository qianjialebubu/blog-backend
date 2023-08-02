package com.example.blog2.po.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.blog2.po.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: myblog-backendv1.5
 * @description: 分类的接收类
 * @author: qjl
 * @create: 2023-07-31 14:05
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExcelType {
    @ExcelProperty(value = "分类编号")
    private Long id;
    @ExcelProperty(value = "分类名称")
    private String name;
//    private List<Blog> blogs;
    @ExcelProperty(value = "分类标签颜色")
    private String color;
    @ExcelProperty(value = "首图地址")
    private String pic_url;
}

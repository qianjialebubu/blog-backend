package com.example.blog2.test.testexcel;

import com.alibaba.excel.EasyExcel;
import com.example.blog2.po.excel.ExcelType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: myblog-backendv1.5
 * @description: 练习excel的导出
 * @author: qjl
 * @create: 2023-07-31 15:14
 **/

public class excelLearn {

    public static void main(String[] args) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeStr = timeFormat.format(new Date());
        System.out.println(timeStr);

//        String filename = "E:\\java\\space\\project\\blog\\test\\demo1.xlsx";
//        // 向Excel中写入数据 也可以通过 head(Class<?>) 指定数据模板
//        EasyExcel.write(filename, ExcelType.class)
//                .sheet("用户信息")
//                .doWrite(getTypeData());
    }

    // 根据Type模板构建数据
    private static List<ExcelType> getTypeData() {
        List<ExcelType> types = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ExcelType excelType = ExcelType.builder()
                    .id((long) i)
                    .name("name")
                    .color("黑色")
                    .pic_url("图片地址")
                    .build();
            types.add(excelType);
        }
        return types;
    }


}

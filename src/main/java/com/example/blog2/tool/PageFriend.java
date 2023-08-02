package com.example.blog2.tool;

import lombok.Data;

/**
 * @program: myblog-backend
 * @description: 友链分页查询
 * @author: qjl
 * @create: 2023-06-21 16:16
 **/
@Data
public class PageFriend {
    private int pagenum;
    private int pagesize;
}

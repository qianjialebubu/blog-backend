package com.example.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog2.po.Pic;

import java.util.List;

/**
 * @author qjl
 * @create 2023-06-20 11:58
 */
public interface PicService  {
    List<String> getAllPic();
    Long deleteByUrl(String url);

    void insertPic(Pic pic);
}

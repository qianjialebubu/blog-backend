package com.example.blog2.service;/**
 * @author qjl
 * @create 2023-06-20 12:01
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog2.dao.PicRepository;

import com.example.blog2.po.Pic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: myblog-backend
 * @description:
 * @author: qjl
 * @create: 2023-06-20 12:01
 **/
@Service
public class PicServiceImpl  implements PicService {

    @Autowired
    private PicRepository picRepository;


    //返回所有的图片的列表
    @Override
    public List<String> getAllPic() {
        return picRepository.getAllPic();
    }

    @Override
    public Long deleteByUrl(String url) {
        picRepository.deleteByFilename(url);
        return null;
//        System.out.println(url);
//        return null;
    }

    @Override
    public void insertPic(Pic pic) {
        picRepository.save(pic);
    }
}

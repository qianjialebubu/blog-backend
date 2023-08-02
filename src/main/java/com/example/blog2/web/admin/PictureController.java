package com.example.blog2.web.admin;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.blog2.dao.PicRepository;
import com.example.blog2.po.Pic;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.Type;
import com.example.blog2.service.PicService;
import com.example.blog2.service.TypeService;
import com.example.blog2.util.AliyunOSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@CrossOrigin
@Configuration
@Api(tags = "后台图片管理接口文档")
public class PictureController {
    @Autowired
    private PicService picService;
    @Autowired
    private PicRepository picRepository;

    @PostMapping(value = "/upload")
    @ResponseBody
    @ApiOperation("上传图片接口文档")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 阿里云存储
        AliyunOSSUtils aliyunOSSUtils = new AliyunOSSUtils();
        aliyunOSSUtils.setEndPoint("填写自己阿里云的endpoint");
        aliyunOSSUtils.setBucketName("填写自己阿里云的keyid");
        aliyunOSSUtils.setKeyId("填写自己阿里云的keysecret");
        aliyunOSSUtils.setKeySecret("填写自己阿里云的bucketname");

        if (file.isEmpty()) {
            return new Result(true, StatusCode.ERROR,"上传图片为空",null);
        }
        JSONObject postData = new JSONObject();
        String fileName = file.getOriginalFilename();//上传的文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀
        fileName = UUID.randomUUID() + suffixName;//生成唯一文件名
        try {
            byte[] fileBytes = file.getBytes();//转换为byte数组
            postData.put("fileName",fileName);
            postData.put("data",fileBytes);
            RestTemplate client = new RestTemplate();
            String contentType = file.getContentType();
            String url = aliyunOSSUtils.uploadImage(fileName, fileBytes, contentType, 1000);
            //FilePath是你服务端的项目接口路径
            System.out.println(postData.get("fileName"));
            return new Result(true, StatusCode.OK,"上传图片成功",url);
        } catch (IOException | JSONException e) {
            System.out.println(e.toString());
        }
        return new Result(true, StatusCode.ERROR,"上传图片失败",null);
    }

    @Transactional
    @PostMapping(value = "/uploadPictureManage")
    @ResponseBody
    @ApiOperation("上传图片接口文档2")
    public Result uploadPictureManage(@RequestParam("file") MultipartFile file) throws Exception {
        // 阿里云存储
        AliyunOSSUtils aliyunOSSUtils = new AliyunOSSUtils();
        aliyunOSSUtils.setEndPoint("填写自己阿里云的endpoint");
        aliyunOSSUtils.setBucketName("填写自己阿里云的keyid");
        aliyunOSSUtils.setKeyId("填写自己阿里云的keysecret");
        aliyunOSSUtils.setKeySecret("填写自己阿里云的bucketname");

        if (file.isEmpty()) {
            return new Result(true, StatusCode.ERROR,"上传图片为空",null);
        }
        JSONObject postData = new JSONObject();
        String fileName = file.getOriginalFilename();//上传的文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀
        fileName = UUID.randomUUID() + suffixName;//生成唯一文件名
        try {
            byte[] fileBytes = file.getBytes();//转换为byte数组
            postData.put("fileName",fileName);
            postData.put("data",fileBytes);
            RestTemplate client = new RestTemplate();
            String contentType = file.getContentType();
            String url = aliyunOSSUtils.uploadImage(fileName, fileBytes, contentType, 1000);
            Pic pic = new Pic();
            pic.setPic_url(url);
            pic.setFilename(fileName);
            //查询最大的id
            Long id = picRepository.SelectMaxId();
//            System.out.println(id);
            pic.setId(id+1);
            //将上传的图片插入到t_pic中
            picService.insertPic(pic);
            //FilePath是你服务端的项目接口路径
//            System.out.println(postData.get("fileName"));
//            System.out.println("上传成功");
            return new Result(true, StatusCode.OK,"上传图片成功",url);
        } catch (IOException | JSONException e) {
            System.out.println(e.toString());
        }
        System.out.println("上传失败");

        return new Result(true, StatusCode.ERROR,"上传图片失败",null);
    }
    @Transactional
    @PostMapping(value = "/uploadPictureManageFixPic")
    @ResponseBody
    @ApiOperation("上传图片接口文档3")
    public Result uploadPictureManageFixPic(@RequestParam("file") MultipartFile file) throws Exception {
        // 阿里云存储
        AliyunOSSUtils aliyunOSSUtils = new AliyunOSSUtils();
        aliyunOSSUtils.setEndPoint("填写自己阿里云的endpoint");
        aliyunOSSUtils.setBucketName("填写自己阿里云的keyid");
        aliyunOSSUtils.setKeyId("填写自己阿里云的keysecret");
        aliyunOSSUtils.setKeySecret("填写自己阿里云的bucketname");

        if (file.isEmpty()) {
            return new Result(true, StatusCode.ERROR,"上传图片为空",null);
        }
        JSONObject postData = new JSONObject();
        String fileName = file.getOriginalFilename();//上传的文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀
        fileName = UUID.randomUUID() + suffixName;//生成唯一文件名
        try {
            byte[] fileBytes = file.getBytes();//转换为byte数组
            postData.put("fileName",fileName);
            postData.put("data",fileBytes);
            RestTemplate client = new RestTemplate();
            String contentType = file.getContentType();
            String url = aliyunOSSUtils.uploadImage(fileName, fileBytes, contentType, 1000);
            Pic pic = new Pic();
            pic.setPic_url(url);
            pic.setFilename(fileName);
            Long id = picRepository.SelectMaxId();
            pic.setId(id+1);
            picService.insertPic(pic);
            return new Result(true, StatusCode.OK,"上传图片成功",url);
        } catch (IOException | JSONException e) {
            System.out.println(e.toString());
        }
        System.out.println("上传失败");

        return new Result(true, StatusCode.ERROR,"上传图片失败",null);
    }

    @GetMapping("/pic/get_all")
    @ApiOperation("获取所有图片文档")
    public List<String> getAll(){

        List<String> allPic = picService.getAllPic();
        return allPic;
    }



    @Transactional
    @GetMapping("pic/delete/{filename}")
    public Result deleteById(@PathVariable String filename){
       picService.deleteByUrl(filename);
       return new Result(true,StatusCode.OK,null);
    }
}

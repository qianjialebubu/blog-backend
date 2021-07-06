package com.example.blog2.web.admin;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/7/5 21:14
 */
@RestController
@CrossOrigin
public class PictureController {
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "失败";
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
            //FilePath是你服务端的项目接口路径
            JSONObject json = client.postForEntity("http://hikari.top/pic_server/upload/upImg", postData, JSONObject.class).getBody();
            System.out.println("上传成功");
            return (String) json.get("data");//返回文件下载地址
        } catch (IOException | JSONException e) {
            System.out.println(e.toString());
        }
        System.out.println("上传失败");
        return "失败";
    }
}

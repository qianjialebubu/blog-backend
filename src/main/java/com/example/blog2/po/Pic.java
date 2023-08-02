package com.example.blog2.po;/**
 * @author qjl
 * @create 2023-06-20 11:54
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
/**
 * @program: myblog-backend
 * @description: 对于图片的保存
 * @author: qjl
 * @create: 2023-06-20 11:54
 **/
@Entity
@Table(name="t_pic")
@Data
public class Pic {
    @Id
    @GeneratedValue
    private Long id;
    private String pic_url;
    private String filename;
}

package com.example.blog2.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

/**
 * @program: myblog-backend
 * @description: 友链实体类
 * @author: qjl
 * @create: 2023-06-21 09:54
 **/
@Data
@Entity
//@Table(name="t_friend")
@TableName("t_friend")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Friend {
    @Id
    @GeneratedValue
    private Long id;

    private String blogName;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private String note;
    private String state;
}

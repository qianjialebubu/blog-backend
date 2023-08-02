package com.example.blog2.temporary.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: myblog-backendv1.5
 * @description: 测试高并发的测试类_抽奖类
 * @author: qjl
 * @create: 2023-07-02 16:49
 **/
@Data
@Entity
@TableName("t_raffle")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Raffle {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer count;
}

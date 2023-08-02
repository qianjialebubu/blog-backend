package com.example.blog2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog2.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author qjl
 * @create 2023-06-22 16:02
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}

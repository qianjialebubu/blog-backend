package com.example.blog2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog2.po.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qjl
 * @create 2023-06-20 21:06
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}

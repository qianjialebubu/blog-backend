package com.example.blog2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog2.po.Type;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/6/13 17:07
 */
@Repository
public interface TypeDao extends BaseMapper<Type> {
    /**
     * 获取每个分类的博客数量
     * @return
     */
    @Select("SELECT DISTINCT t.type_id, t.type_name, COUNT(b.type_id) type_count " +
            "FROM type t LEFT OUTER JOIN blog b " +
            "ON t.type_id = b.type_id " +
            "GROUP BY t.type_id " +
            "ORDER BY COUNT(b.type_id) DESC")
    List<Type> getTypeCount();
}

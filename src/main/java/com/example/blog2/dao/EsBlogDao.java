package com.example.blog2.dao;
/**
 * @program: springtest
 * @description:
 * @author: qjl
 * @create: 2023-06-25 09:01
 **/

import com.example.blog2.po.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsBlogDao extends ElasticsearchRepository<EsBlog, Long>{

}


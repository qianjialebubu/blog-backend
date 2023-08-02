package com.example.blog2;

/**
 * @program: springtest
 * @description: 文档测试
 * @author: qjl
 * @create: 2023-06-25 09:11
 **/

import com.example.blog2.dao.EsBlogDao;
import com.example.blog2.po.EsBlog;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {

    @Autowired
    private EsBlogDao esBlogDao;
    /**
     * term 查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象
     */
    @Test
    public void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "异常");
        long start_time = System.currentTimeMillis();
        Iterable<EsBlog> blogs = esBlogDao.search(termQueryBuilder);
        long end_time = System.currentTimeMillis();
        System.out.println(end_time-start_time);
        for (EsBlog blog : blogs) {
            System.out.println(blog);
        }
    }
    /**
     * term 查询加分页
     */
//    @Test
//    public void termQueryByPage(){
//        int currentPage= 0 ;
//        int pageSize = 5;
//        //设置查询分页
//        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米");
//        Iterable<Product> products = productDao.search(termQueryBuilder,pageRequest);
//        for (Product product : products) {
//            System.out.println(product);
//        }
//    }

}


package com.example.blog2;

/**
 * @program: springtest
 * @description: 文档测试
 * @author: qjl
 * @create: 2023-06-25 09:09
 **/


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog2.dao.BlogRepository;
import com.example.blog2.dao.EsBlogDao;
import com.example.blog2.mapper.BlogMapper;
import com.example.blog2.po.Blog;
import com.example.blog2.po.EsBlog;
import com.example.blog2.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {

    @Autowired
    private EsBlogDao esBlogDao;
    @Autowired
    private BlogRepository blogRepository;
    /**
     * 新增es数据库中所有的博客内容
     */
    @Test
    public void saveAllBlog(){

        List<Blog> blogs = blogRepository.findAll();
        blogs.forEach(blog -> {
            EsBlog esBlog = new EsBlog();
            esBlog.setId(blog.getId());
            esBlog.setTitle(blog.getTitle());
            esBlog.setContent(blog.getContent());
            esBlogDao.save(esBlog);

        });
    }
    //POSTMAN, GET http://localhost:9200/product/_doc/2

    //修改
//    @Test
//    public void update(){
//        Product product = new Product();
//        product.setId(2L);
//        product.setTitle("小米 2 手机");
//        product.setCategory("手机");
//        product.setPrice(9999.0);
//        product.setImages("http://www.atguigu/xm.jpg");
//        product.setContent("测试");
//        productDao.save(product);
//    }
//    //POSTMAN, GET http://localhost:9200/product/_doc/2
//
//
//    //根据 id 查询
//    @Test
//    public void findById(){
//        Product product = productDao.findById(2L).get();
//        System.out.println(product);
//    }
//
//    @Test
//    public void findAll(){
//        Iterable<Product> products = productDao.findAll();
//        for (Product product : products) {
//            System.out.println(product);
//        }
//    }
//
//    //删除
//    @Test
//    public void delete(){
//        Product product = new Product();
//        product.setId(2L);
//        productDao.delete(product);
//    }
//    //POSTMAN, GET http://localhost:9200/product/_doc/2
//
//    //批量新增
//    @Test
//    public void saveAll(){
//        List<Product> productList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setId(Long.valueOf(i));
//            product.setTitle("["+i+"]小米手机");
//            product.setCategory("手机");
//            product.setPrice(1999.0 + i);
//            product.setContent("测试");
//            product.setImages("http://www.atguigu/xm.jpg");
//            productList.add(product);
//        }
//        Product product = new Product();
//        product.setId(Long.valueOf(12));
//        product.setTitle("["+12+"]小米手机");
//        product.setCategory("手机");
//        product.setPrice(1999.0 + 12);
//        product.setContent("# 异常\n" +
//                "Exception 和 Error 有什么区别？在 Java 中，所有的异常都有一个共同的祖先 java.lang 包中的 Throwable 类。Throwable 类有两个重要的子类:Exception :程序本身可以处理的异常");
//        product.setImages("http://www.atguigu/xm.jpg");
//        productList.add(product);
//        productDao.saveAll(productList);
//    }
//
//    //分页查询
//    @Test
//    public void findByPageable(){
//        //设置排序(排序方式，正序还是倒序，排序的 id)
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        int currentPage=0;//当前页，第一页从 0 开始， 1 表示第二页
//        int pageSize = 5;//每页显示多少条
//        //设置查询分页
//        PageRequest pageRequest = PageRequest.of(currentPage, pageSize,sort);
//        //分页查询
//        Page<Product> productPage = productDao.findAll(pageRequest);
//        for (Product Product : productPage.getContent()) {
//            System.out.println(Product);
//        }
//    }
}


package com.example.blog2.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog2.mapper.BlogMapper;
import com.example.blog2.po.Blog;
import com.example.blog2.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BlogService extends IService<Blog> {
//    根据id查询
    Blog getBlog(Long id);
//    分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Blog getAndConvert(Long id);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    Long countViews();

    List<String> ViewCountByMonth();

    List<String> BlogCountByMonth();

    List<String> appreciateCountByMonth();

    Long countAppreciate();

    Long countComment();

}

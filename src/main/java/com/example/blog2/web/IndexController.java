package com.example.blog2.web;

import com.alibaba.fastjson.JSON;
import com.example.blog2.po.*;
import com.example.blog2.po.dto.appreciationBlog;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import com.example.blog2.vo.BlogQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(tags = "首页博客列表接口文档")
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    @ApiOperation("获取博客列表接口文档")
    public Result getBlogList(@RequestHeader Map<String,Object>para, @RequestParam String query, @RequestParam String pagenum, @RequestParam String pagesize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pagenum)-1, Integer.parseInt(pagesize),sort);
        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable));
    }

    @GetMapping("/getRecommendBlogList")
    @ApiOperation("获取推荐博客接口文档")
    public Result getRecommendBlogList() {
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(8));
    }

    @GetMapping("/search")
    @ApiOperation("获取搜索博客接口文档")
    public Result search(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query){

        return new Result(true,StatusCode.OK,"获取搜索博客成功",blogService.listBlog(query,pageable));
    }

    @GetMapping("/blog/{id}")
    @ApiOperation("根据id获取博客接口文档")
    public Result blog(@PathVariable Long id) {
        return new Result(true, StatusCode.OK, "获取博客成功", blogService.getAndConvert(id));
    }

    @GetMapping("/footer/newblog")
    @ApiOperation("获取推荐博客接口文档")
    public Result newblogs(){
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(3));
    }

    @GetMapping("/types/{id}")
    @ApiOperation("获取分类博客列表接口文档")
    public Result types(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id){
        List<Type> types = typeService.listType();
        if (id == -1 && types.size()>0){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        return new Result(true,StatusCode.OK,"获取分类博客列表成功",blogService.listBlog(pageable,blogQuery));
    }

    @GetMapping("tags/{id}")
    @ApiOperation("获取标签博客列表接口文档")
    public Result tags(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                       @PathVariable Long id){
        List<Tag> tags = tagService.listTag();
        if (id == -1 &&tags.size()>0 ){
            id = tags.get(0).getId();
        }
        return new Result(true,StatusCode.OK,"获取标签博客列表成功",blogService.listBlog(id,pageable));
    }

    @PostMapping("/blog/appreciationBlog")
    @ApiOperation("对博客进行点赞接口文档")
    public Result appreciationBlog(@RequestBody Map<String,String> para){
//        System.out.println(para.get("blogId"));
        Long blogId = Long.valueOf(Integer.parseInt(para.get("blogId")));
        Blog blog = blogService.getBlog(blogId);
        blog.setAppreciation(blog.getAppreciation()+1);
        blogService.updateBlog(blogId,blog);
        return null;
    }

}

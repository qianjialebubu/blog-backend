package com.example.blog2.web;

import com.example.blog2.po.*;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import com.example.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/3/30 17:52
 */
@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/getBlogList")
    public Result getBlogList(@RequestParam String query,@RequestParam String pagenum,@RequestParam String pagesize) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pagenum)-1, Integer.parseInt(pagesize),sort);
        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable));
    }


    @GetMapping("/getRecommendBlogList")
    public Result getRecommendBlogList() {
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(8));
    }

    @GetMapping("/search")
    public Result search(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query){
        System.out.println(query);
        return new Result(true,StatusCode.OK,"获取搜索博客成功",blogService.listBlog("%"+query+"%",pageable));
    }

    @GetMapping("/blog/{id}")
    public Result blog(@PathVariable Long id) {
        return new Result(true, StatusCode.OK, "获取博客成功", blogService.getAndConvert(id));
    }

    @GetMapping("/footer/newblog")
    public Result newblogs(){
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(3));
    }

    @GetMapping("/types/{id}")
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
    public Result tags(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                       @PathVariable Long id){
        List<Tag> tags = tagService.listTag();
        if (id == -1 &&tags.size()>0 ){
            id = tags.get(0).getId();
        }
        return new Result(true,StatusCode.OK,"获取分类博客列表成功",blogService.listBlog(id,pageable));
    }

}

package com.example.blog2.web;

import com.example.blog2.po.*;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/3/30 17:52
 */
@Controller
@CrossOrigin
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/getBlogList")
    @ResponseBody
    public Result getBlogList(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC)Pageable pageable) {
        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable).getContent());
    }

    @GetMapping("/getTypeList")
    @ResponseBody
    public Result getTypeList() {
        return new Result(true, StatusCode.OK, "获取博客分类成功",typeService.listTypeTop(6));
    }

    @GetMapping("/getTagList")
    @ResponseBody
    public Result getTagList() {
        return new Result(true, StatusCode.OK, "获取博客标签成功", tagService.listTagTop(10));
    }

    @GetMapping("/getRecommendBlogList")
    @ResponseBody
    public Result getRecommendBlogList() {
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(8));
    }

//    @PostMapping("/search")
//    public String search(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,
//                         @RequestParam String query,Model model){
//        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
//        model.addAttribute("query",query);
//        return "search";
//    }
//
//    @GetMapping("/blog/{id}")
//    public String blog(@PathVariable Long id,Model model) {
//        model.addAttribute("blog",blogService.getAndConvert(id));
//        return "blog";
//    }
//
    @GetMapping("/footer/newblog")
    @ResponseBody
    public Result newblogs(){
        return new Result(true, StatusCode.OK, "获取推荐博客成功", blogService.listRecommendBlogTop(3));
    }

}

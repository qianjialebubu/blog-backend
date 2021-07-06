package com.example.blog2.web.admin;

import com.example.blog2.po.Blog;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.User;
import com.example.blog2.service.BlogService;
import com.example.blog2.service.TagService;
import com.example.blog2.service.TypeService;
import com.example.blog2.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/4/1 11:41
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/getBlogList")
    public Result getBlogList(@RequestParam String query, @RequestParam String pagenum, @RequestParam String pagesize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pagenum) - 1, Integer.parseInt(pagesize), sort);
        return new Result(true, StatusCode.OK, "获取博客列表成功", blogService.listBlog(pageable));
    }

//    @PostMapping("/blogs")
//    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
//        blog.setUser((User) session.getAttribute("user"));
//        blog.setType(typeService.getType(blog.getType().getId()));
//        blog.setTags(tagService.listTag(blog.getTagIds()));
//        Blog b;
//        if (blog.getId() == null) {
//            b = blogService.saveBlog(blog);
//        } else {
//            b = blogService.updateBlog(blog.getId(), blog);
//        }
//        if (b == null) {
//            attributes.addFlashAttribute("message", "操作失败");
//        } else {
//            attributes.addFlashAttribute("message", "操作成功");
//        }
//        return REDIRECT_LIST;
//    }

    //    @PostMapping("/blogs/search")
//    public String search(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
//                         BlogQuery blog, Model model) {
//        model.addAttribute("page", blogService.listBlog(pageable, blog));
//        return "admin/blogs :: blogList";
//    }
    @GetMapping("/search")
    public Result search(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query) {
        System.out.println(query);
        return new Result(true, StatusCode.OK, "获取搜索博客成功", blogService.listBlog("%" + query + "%", pageable));
    }


//    @GetMapping("/blogs/input")
//    public String input(Model model) {
//        setTypeAndTag(model);
//        model.addAttribute("blog", new Blog());
//        return INPUT;
//    }
//
//    @GetMapping("/blogs/{id}/input")
//    public String editInput(@PathVariable Long id, Model model) {
//        setTypeAndTag(model);
//        Blog blog = blogService.getBlog(id);
//        blog.init();
//        model.addAttribute("blog", blog);
//        return INPUT;
//    }

    @GetMapping("/blogs/{id}/delete")
    public Result delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return new Result();
    }

//    private void setTypeAndTag(Model model) {
//        model.addAttribute("types", typeService.listType());
//        model.addAttribute("tags", tagService.listTag());
//    }

}

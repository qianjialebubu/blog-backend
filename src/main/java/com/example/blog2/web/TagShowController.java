//package com.example.blog2.web;
//
//import com.example.blog2.po.Tag;
//import com.example.blog2.po.Type;
//import com.example.blog2.service.BlogService;
//import com.example.blog2.service.TagService;
//import com.example.blog2.service.TypeService;
//import com.example.blog2.vo.BlogQuery;
//import net.bytebuddy.TypeCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
///**
// * @author zhaomin_2017013792_CS181
// * @version 1.0
// * @date 2021/4/19 22:00
// */
//@Controller
//public class TagShowController {
//    @Autowired
//    private TagService tagService;
//
//    @Autowired
//    private BlogService blogService;
//
////    @GetMapping("tags/{id}")
////    public String tags(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
////                       @PathVariable Long id, Model model){
////        List<Tag> tags = tagService.listTag();
////        if (id == -1 &&tags.size()>0 ){
////            id = tags.get(0).getId();
////        }
////        model.addAttribute("tags",tags);
////        model.addAttribute("page",blogService.listBlog(id,pageable));
////        model.addAttribute("activeTagId",id);
////        return "tags";
////    }
//}

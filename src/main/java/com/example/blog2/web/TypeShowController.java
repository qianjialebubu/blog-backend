//package com.example.blog2.web;
//
//import com.example.blog2.po.Type;
//import com.example.blog2.service.BlogService;
//import com.example.blog2.service.TypeService;
//import com.example.blog2.vo.BlogQuery;
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
//
//@Controller
//public class TypeShowController {
//
////    @Autowired
////    private TypeService typeService;
////    @Autowired
////    private BlogService blogService;
//
////    @GetMapping("/types/{id}")
////    public String types(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
////                        @PathVariable Long id, Model model){
////        List<Type> types = typeService.listType();
////        if (id == -1 && types.size()>0){
////            id = types.get(0).getId();
////        }
////        BlogQuery blogQuery = new BlogQuery();
////        blogQuery.setTypeId(id);
////        model.addAttribute("types",types);
////        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
////        model.addAttribute("activeTypeId",id);
////        return "types";
////    }
//}

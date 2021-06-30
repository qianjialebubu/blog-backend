//package com.example.blog2.web;
//
//import com.example.blog2.po.Comment;
//import com.example.blog2.po.User;
//import com.example.blog2.service.BlogService;
//import com.example.blog2.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
///**
// * @author zhaomin_2017013792_CS181
// * @version 1.0
// * @date 2021/4/20 9:58
// */
//@Controller
//
//public class CommentController {
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private BlogService blogService;
//
//    private final String avatar = "/images/avatar.png";
////
////    @GetMapping("/comments/{blogId}")
////    public String comments(@PathVariable Long blogId, Model model){
////        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
////        return "blog :: commentList";
////    }
////
////    @PostMapping("/comments")
////    public String post(Comment comment, HttpSession session){
////        Long blogId = comment.getBlog().getId();
////        System.out.println("-------"+comment);
////        comment.setBlog(blogService.getBlog(blogId));
////        User user = (User) session.getAttribute("user");
////        if (user != null) {
////            comment.setAvatar(user.getAvatar());
////            comment.setAdminComment(true);
////        } else {
////            comment.setAvatar(avatar);
////        }
////        commentService.saveComment(comment);
////        return "redirect:/comments/" + blogId;
////    }
//
//}

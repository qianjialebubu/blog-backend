//package com.example.blog2.web.admin;
//
//import com.example.blog2.po.User;
//import com.example.blog2.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//
///**
// * @author zhaomin_2017013792_CS181
// * @version 1.0
// * @date 2021/3/31 23:10
// */
//
//@Controller
//@RequestMapping("/admin")
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public String loginPage() {
//        return "admin/login";
//    }
//
//    @PostMapping("login")
//    public String login(@RequestParam String username, @RequestParam String password,
//                        HttpSession session, RedirectAttributes attributes) {
//        System.out.println(password);
//        User user = userService.checkUser(username, password);
//        if (user != null) {
//            System.out.println(user.getPassword());
//            user.setPassword(null);
//            session.setAttribute("user", user);
//            return "admin/index";
//        } else {
//            attributes.addFlashAttribute("message","用户名和密码错误");
//            return "redirect:/admin";
//        }
//    }
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute("user");
//        return "redirect:/admin";
//    }
//}

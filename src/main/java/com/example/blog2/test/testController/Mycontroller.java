package com.example.blog2.test.testController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: myblog-backendv1.3
 * @description: 测试接口
 * @author: qjl
 * @create: 2023-06-23 15:35
 **/

@Controller
public class Mycontroller {
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","Hello world!");
        return "index";
    }
}

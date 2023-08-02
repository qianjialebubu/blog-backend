package com.example.blog2.temporary.controller;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.temporary.service.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: myblog-backendv1.5
 * @description: 控制类
 * @author: qjl
 * @create: 2023-07-02 17:00
 **/
@RestController
@RequestMapping("/raffle")
public class RaffleController {
    @Autowired
    private RaffleService raffleService;
    @GetMapping("/get_raffle")
    public Result get_Raffle(){
        Boolean aBoolean = raffleService.getone();
        if(aBoolean){
            return new Result(true, StatusCode.OK, "成功");
        }else {
            return new Result(false, StatusCode.ERROR, "失败");
        }
    }
}

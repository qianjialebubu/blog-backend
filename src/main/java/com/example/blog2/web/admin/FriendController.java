package com.example.blog2.web.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.blog2.dao.FriendRepository;
import com.example.blog2.po.Friend;
import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.service.FriendService;
import com.example.blog2.tool.PageFriend;
import com.example.blog2.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: myblog-backend
 * @description: friend的控制层
 * @author: qjl
 * @create: 2023-06-21 10:11
 **/
@RestController
@RequestMapping(value = "/admin/friend/")
@CrossOrigin
@Api(tags = "友链接口文档")
public class FriendController {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private FriendService friendService;

    @GetMapping("/getFriendList")
    @ApiOperation("返回所有友链信息")
    public Result getAllFriend(){

        List<Friend> listFriend = friendService.getAllFriendList();
        return new Result(true, StatusCode.OK, "获取友链列表成功",listFriend);
    }

    @GetMapping("/{id}/delete")
    @ApiOperation("根据友链id删除友链")
    public Result deleteFriend(@PathVariable Long id){
//        System.out.println(id);
        friendService.deleteFriend(id);
        return new Result(true, StatusCode.OK, "删除链接成功");
    }

    @PostMapping("/createFriend")
    @ApiOperation("修改友链")
    public Result createFriend(@RequestBody Map<String,Friend> para){
        Friend friend = para.get("Friend");
        //修改id所式的记录
        friendService.modifyById(friend);
        return new Result(true, StatusCode.OK, "修改链接成功");
    }

    @PostMapping("/insertFriend")
    @ApiOperation("新增友链")
    public Result insertFriend(@RequestBody Map<String,Friend> para){
        //新增记录
        Friend friend = para.get("Friend");
        Friend friend1 = friendService.getOne(new LambdaQueryWrapper<Friend>().orderByDesc(Friend::getId).last("limit 1"));
        friend.setCreateTime(new Date());
        friend.setId(friend1.getId()+1L);
        friendService.insertFriend(friend);
        return new Result(true, StatusCode.OK, "修改链接状态成功");
    }

    @PostMapping("/getPageFriendsList")
    @ApiOperation("分页查询友链列表")
    public Result getPageFriendsList(@RequestBody Map<String, PageFriend> para){
        PageFriend pageFriend = para.get("page");
        List<Friend> listFriend = friendService.pageFriends(pageFriend);
        return new Result(true, StatusCode.OK, "分页查找成功",listFriend);
    }
}

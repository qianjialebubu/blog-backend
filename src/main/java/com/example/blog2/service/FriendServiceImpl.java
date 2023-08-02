package com.example.blog2.service;/**
 * @author qjl
 * @create 2023-06-21 10:05
 */


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog2.mapper.FriendMapper;
import com.example.blog2.po.Friend;
import com.example.blog2.tool.PageFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: myblog-backend
 * @description: 友链实现类
 * @author: qjl
 * @create: 2023-06-21 10:05
 **/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService{

    @Autowired
    private FriendMapper friendMapper;
    @Override
    public List<Friend> getAllFriendList() {
        List<Friend> friends = friendMapper.selectList(null);
//        System.out.println(friends);
        return friends;
    }

    //根据链接的id删除链接
    @Override
    public void deleteFriend(Long id) {
        friendMapper.deleteById(id);
    }

    //根据id修改记录
    @Override
    public void modifyById(Friend friend) {
        deleteFriend(friend.getId());
//        System.out.println(friend.toString());
        friendMapper.insert(friend);
    }

    //新增友链记录
    @Override
    public void insertFriend(Friend friend) {
        friendMapper.insert(friend);
//        System.out.println(friend.toString());
    }

    @Override
    public List<Friend> pageFriends(PageFriend pageFriend) {
        Page<Friend> page = new Page<>(pageFriend.getPagenum(),pageFriend.getPagesize());
//        Page<Friend> page=new Page<>(2,1);
//        IPage<Friend> page = new Page<>();
//        QueryWrapper<Object> wrapper = new QueryWrapper<>();
//        wrapper.equals()
        Page<Friend> friendPage = friendMapper.selectPage(page, null);
//        System.out.println(page.getRecords());
        List<Friend> records = friendPage.getRecords();
//        System.out.println(records);
//        System.out.println(friendPage.getRecords());
        return records;
    }
}

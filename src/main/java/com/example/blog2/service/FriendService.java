package com.example.blog2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog2.po.Friend;
import com.example.blog2.tool.PageFriend;

import java.util.List;

/**
 * @author qjl
 * @create 2023-06-21 10:04
 */
public interface FriendService extends IService<Friend> {
    List<Friend> getAllFriendList();

    void deleteFriend(Long id);

    void modifyById(Friend friend);

    void insertFriend(Friend friend);

    List<Friend> pageFriends(PageFriend pageFriend);
}

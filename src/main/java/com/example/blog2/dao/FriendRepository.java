package com.example.blog2.dao;

import com.example.blog2.po.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author qjl
 * @create 2023-06-21 10:06
 */
public interface FriendRepository extends JpaRepository<Friend,Long> {
    @Query("SELECT MAX(id) FROM Friend")
    Long getMaxId();
}

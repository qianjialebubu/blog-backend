package com.example.blog2.dao;

import com.example.blog2.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User selectUserByName(String username);

}

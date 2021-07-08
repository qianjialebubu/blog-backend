package com.example.blog2.service;

import com.example.blog2.po.Comment;

import java.util.List;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/4/20 15:56
 */

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    List<Comment> listComment();

    List<String> CommentCountByMonth();

}

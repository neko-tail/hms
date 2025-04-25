package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Comment;
import net.thmaster.hms.model.req.CommentInfoRequest;

import java.util.List;

/**
 * @author master
 */
public interface CommentService {

    Comment save(Long blogId, Long userId, CommentInfoRequest info);

    Comment get(Long blogId, Long userId, Long commentId);

    List<Comment> list(Long blogId, Long userId);

    Comment update(Long blogId, Long userId, Long commentId, CommentInfoRequest info);

    void delete(Long blogId, Long userId, Long commentId);

}

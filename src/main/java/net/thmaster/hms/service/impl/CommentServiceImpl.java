package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Comment;
import net.thmaster.hms.model.req.CommentInfoRequest;
import net.thmaster.hms.repository.CommentRepository;
import net.thmaster.hms.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment save(Long blogId, Long userId, CommentInfoRequest info) {
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setUserId(userId);
        comment.setContent(info.content());

        commentRepository.save(comment);

        return get(blogId, userId, comment.getId());
    }

    @Override
    public Comment get(Long blogId, Long userId, Long commentId) {
        return commentRepository.getOptById(commentId)
                .filter(x -> Objects.equals(x.getBlogId(), blogId))
                .filter(x -> Objects.equals(x.getUserId(), userId))
                .orElseThrow(() -> new ResourceNotFoundException("评论不存在"));
    }

    @Override
    public List<Comment> list(Long blogId, Long userId) {
        return commentRepository.lambdaQuery()
                .eq(Comment::getBlogId, blogId)
                .eq(Comment::getUserId, userId)
                .list();
    }

    @Override
    public Comment update(Long blogId, Long userId, Long commentId, CommentInfoRequest info) {
        commentRepository.lambdaUpdate()
                .set(info.content() != null, Comment::getContent, info.content())
                .eq(Comment::getBlogId, blogId)
                .eq(Comment::getUserId, userId)
                .eq(Comment::getId, commentId)
                .update();

        return get(blogId, userId, commentId);
    }

    @Override
    public void delete(Long blogId, Long userId, Long commentId) {
        commentRepository.removeById(commentId);
    }

}

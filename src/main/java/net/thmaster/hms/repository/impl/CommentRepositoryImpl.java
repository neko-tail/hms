package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.mapper.CommentMapper;
import net.thmaster.hms.model.entity.Comment;
import net.thmaster.hms.repository.CommentRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class CommentRepositoryImpl extends MPJBaseServiceImpl<CommentMapper, Comment> implements CommentRepository {

}

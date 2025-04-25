package net.thmaster.hms.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import net.thmaster.hms.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author master
 */
@Mapper
public interface CommentMapper extends MPJBaseMapper<Comment> {

}

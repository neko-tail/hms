package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.mapper.BlogMapper;
import net.thmaster.hms.model.entity.Blog;
import net.thmaster.hms.repository.BlogRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class BlogRepositoryImpl extends MPJBaseServiceImpl<BlogMapper, Blog> implements BlogRepository {

}

package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Blog;
import net.thmaster.hms.model.req.BlogInfoRequest;
import net.thmaster.hms.model.req.query.BlogQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface BlogService {

    Blog save(BlogInfoRequest info);

    Blog get(Long foodId);

    List<Blog> list(BlogQueryRequest query);

    Blog update(Long foodId, BlogInfoRequest info);

    void delete(Long foodId);

}

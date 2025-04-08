package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Blog;
import net.thmaster.hms.model.req.BlogInfoRequest;
import net.thmaster.hms.model.req.query.BlogQueryRequest;
import net.thmaster.hms.repository.BlogRepository;
import net.thmaster.hms.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public Blog save(BlogInfoRequest info) {
        Blog blog = new Blog();
        blog.setTitle(info.title());
        blog.setAuthor(info.author());
        blog.setContent(info.content());

        blogRepository.save(blog);

        return get(blog.getId());
    }

    @Override
    public Blog get(Long blogId) {
        return blogRepository.getOptById(blogId).orElseThrow(() -> new ResourceNotFoundException("食物不存在"));
    }

    @Override
    public List<Blog> list(BlogQueryRequest query) {
        return blogRepository.lambdaQuery()
                .func(query != null, w -> w
                        .like(query != null && query.title() != null, Blog::getTitle, query.title())
                        .like(query != null && query.author() != null, Blog::getAuthor, query.author())
                ).list();
    }

    @Override
    public Blog update(Long blogId, BlogInfoRequest info) {
        blogRepository.lambdaUpdate()
                .set(info.title() != null, Blog::getTitle, info.title())
                .set(info.author() != null, Blog::getAuthor, info.author())
                .set(info.content() != null, Blog::getContent, info.content())
                .eq(Blog::getId, blogId)
                .update();

        return get(blogId);
    }

    @Override
    public void delete(Long blogId) {
        blogRepository.removeById(blogId);
    }

}

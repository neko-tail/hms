package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.BlogModelAssembler;
import net.thmaster.hms.model.model.BlogModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.BlogInfoRequest;
import net.thmaster.hms.model.req.query.BlogQueryRequest;
import net.thmaster.hms.service.BlogService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 博客 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/blogs")
@Tag(name = "blogs", description = "博客")
public class BlogController {

    private final BlogService blogService;

    private final BlogModelAssembler blogModelAssembler;

    @Operation(summary = "创建博客", description = "创建一条博客")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建博客"),
    })
    @PostMapping
    public ResponseEntity<BlogModel> create(@Valid @RequestBody final BlogInfoRequest info) {
        return ResponseEntity.ok(blogModelAssembler.toModel(blogService.save(info)));
    }

    @Operation(summary = "获取博客列表", description = "根据查询参数获取博客列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取博客列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<BlogModel>> list(final BlogQueryRequest query) {
        return ResponseEntity.ok(blogModelAssembler.toCollectionModel(blogService.list(query), query));
    }

    @Operation(summary = "获取博客", description = "根据博客 ID 获取博客")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取博客"),
            @ApiResponse(responseCode = "404", description = "博客未找到")
    })
    @GetMapping("/{blogId}")
    public ResponseEntity<BlogModel> get(@PathVariable("blogId") Long blogId) {
        return ResponseEntity.ok(blogModelAssembler.toModel(blogService.get(blogId)));
    }

    @Operation(summary = "更新博客", description = "根据博客 ID 更新博客")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新博客"),
            @ApiResponse(responseCode = "404", description = "博客未找到")
    })
    @PutMapping("/{blogId}")
    public ResponseEntity<BlogModel> update(
            @PathVariable("blogId") Long blogId,
            @Valid @RequestBody final BlogInfoRequest info
    ) {
        return ResponseEntity.ok(blogModelAssembler.toModel(blogService.update(blogId, info)));
    }

    @Operation(summary = "删除博客", description = "根据博客 ID 删除博客")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除博客"),
            @ApiResponse(responseCode = "404", description = "博客未找到")
    })
    @DeleteMapping("/{blogId}")
    public ResponseEntity<VoidModel> delete(@PathVariable("blogId") Long blogId) {
        blogService.delete(blogId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(BlogController.class).list(null)).withRel("blogs"))
        );
    }

}

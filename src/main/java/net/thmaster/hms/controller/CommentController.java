package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.CommentModelAssembler;
import net.thmaster.hms.model.model.CommentModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.CommentInfoRequest;
import net.thmaster.hms.service.CommentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/blogs/{blogId}/{userId}/comments")
@Tag(name = "comments", description = "评论")
public class CommentController {

    private final CommentService commentService;

    private final CommentModelAssembler commentModelAssembler;

    @Operation(summary = "创建评论", description = "创建一条评论")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建评论"),
    })
    @PostMapping
    public ResponseEntity<CommentModel> create(
            @PathVariable("blogId") Long blogId,
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final CommentInfoRequest info
    ) {
        return ResponseEntity.ok(commentModelAssembler.toModel(commentService.save(blogId, userId, info)));
    }

    @Operation(summary = "获取评论列表", description = "根据查询参数获取评论列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取评论列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<CommentModel>> list(
            @PathVariable("blogId") Long blogId,
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(commentModelAssembler.toCollectionModel(commentService.list(blogId, userId)));
    }

    @Operation(summary = "获取评论", description = "根据评论 ID 获取评论")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取评论"),
            @ApiResponse(responseCode = "404", description = "评论未找到")
    })
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentModel> get(
            @PathVariable("blogId") Long blogId,
            @PathVariable("userId") Long userId,
            @PathVariable("commentId") Long commentId
    ) {
        return ResponseEntity.ok(commentModelAssembler.toModel(commentService.get(blogId, userId, commentId)));
    }

    @Operation(summary = "更新评论", description = "根据评论 ID 更新评论")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新评论"),
            @ApiResponse(responseCode = "404", description = "评论未找到")
    })
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentModel> update(
            @PathVariable("blogId") Long blogId,
            @PathVariable("userId") Long userId,
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody final CommentInfoRequest info
    ) {
        return ResponseEntity.ok(commentModelAssembler.toModel(commentService.update(blogId, userId, commentId, info)));
    }

    @Operation(summary = "删除评论", description = "根据评论 ID 删除评论")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除评论"),
            @ApiResponse(responseCode = "404", description = "评论未找到")
    })
    @DeleteMapping("/{commentId}")
    public ResponseEntity<VoidModel> delete(
            @PathVariable("blogId") Long blogId,
            @PathVariable("userId") Long userId,
            @PathVariable("commentId") Long commentId
    ) {
        commentService.delete(blogId, userId, commentId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(CommentController.class).list(blogId, userId)).withRel("comments"))
        );
    }

}

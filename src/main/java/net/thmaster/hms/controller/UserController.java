package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.UserModelAssembler;
import net.thmaster.hms.model.entity.User;
import net.thmaster.hms.model.model.UserModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.RegisterRequest;
import net.thmaster.hms.model.req.UserInfoRequest;
import net.thmaster.hms.model.req.query.UserQueryRequest;
import net.thmaster.hms.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users")
@Tag(name = "users", description = "用户")
public class UserController {

    private final UserService userService;

    private final UserModelAssembler userModelAssembler;

    @Operation(summary = "创建用户（注册）", description = "使用用户名、密码及其他可选信息创建一个新用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建用户",
                    content = {@Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "409", description = "用户名或邮箱已存在")
    })
    @PostMapping
    public ResponseEntity<UserModel> register(@Valid @RequestBody final RegisterRequest register) {
        return ResponseEntity.ok(userModelAssembler.toModel(userService.register(register)));
    }

    @Operation(summary = "获取用户列表", description = "根据查询条件，获取用户列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户列表"),
    })
    @GetMapping
    public ResponseEntity<CollectionModel<UserModel>> list(UserQueryRequest query) {
        return ResponseEntity.ok(userModelAssembler.toCollectionModel(userService.list(query), query));
    }

    @Operation(summary = "获取用户信息", description = "根据用户 ID 获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息",
                    content = {@Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> get(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userModelAssembler.toModel(userService.get(userId)));
    }

    @Operation(summary = "更新用户信息", description = "根据用户 ID 更新用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户信息",
                    content = {@Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "请求参数无效"),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> update(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final UserInfoRequest info
    ) {
        return ResponseEntity.ok(userModelAssembler.toModel(userService.update(userId, info)));
    }

    @Operation(summary = "删除用户", description = "根据用户 ID 删除用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除用户",
                    content = {@Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<VoidModel> delete(@PathVariable("userId") Long userId) {
        userService.delete(userId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(UserController.class).list(null)).withSelfRel()));
    }
}

package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.UserCustomModelAssembler;
import net.thmaster.hms.model.model.UserCustomModel;
import net.thmaster.hms.model.req.UserCustomInfoRequest;
import net.thmaster.hms.service.UserCustomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户自定义数据 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/custom")
@Tag(name = "user-custom", description = "用户自定义数据")
public class UserCustomController {

    private final UserCustomService userCustomService;

    private final UserCustomModelAssembler userCustomModelAssembler;

    @Operation(summary = "获取用户自定义数据", description = "根据用户 ID 获取用户自定义数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户自定义数据"),
            @ApiResponse(responseCode = "404", description = "用户自定义数据未找到")
    })
    @GetMapping
    public ResponseEntity<UserCustomModel> get(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userCustomModelAssembler.toModel(userCustomService.get(userId)));
    }

    @Operation(summary = "更新用户自定义数据", description = "根据用户 ID 更新用户自定义数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户自定义数据"),
            @ApiResponse(responseCode = "404", description = "用户自定义数据未找到")
    })
    @PutMapping
    public ResponseEntity<UserCustomModel> update(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final UserCustomInfoRequest info
    ) {
        return ResponseEntity.ok(userCustomModelAssembler.toModel(userCustomService.update(userId, info)));
    }

}

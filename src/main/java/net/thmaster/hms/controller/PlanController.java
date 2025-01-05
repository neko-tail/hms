package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.PlanModelAssembler;
import net.thmaster.hms.model.model.PlanModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.PlanInfoRequest;
import net.thmaster.hms.model.req.query.PlanQueryRequest;
import net.thmaster.hms.service.PlanService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 训练计划 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/plans")
@Tag(name = "plans", description = "训练计划")
public class PlanController {

    private final PlanService planService;

    private final PlanModelAssembler planModelAssembler;

    @Operation(summary = "创建用户训练计划", description = "在指定用户下创建一条训练计划")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建用户训练计划")
    })
    @PostMapping
    public ResponseEntity<PlanModel> create(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final PlanInfoRequest info
    ) {
        return ResponseEntity.ok(planModelAssembler.toModel(planService.save(userId, info)));
    }

    @Operation(summary = "获取用户训练计划列表", description = "根据查询条件，获取用户符合条件的训练计划列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户训练计划列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<PlanModel>> list(
            @PathVariable("userId") Long userId,
            final PlanQueryRequest query
    ) {
        return ResponseEntity.ok(CollectionModel.of(planModelAssembler.toCollectionModel(
                planService.list(userId, query), userId, query
        )));
    }

    @Operation(summary = "获取用户训练计划", description = "根据训练计划 ID 获取用户训练计划")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户训练计划"),
            @ApiResponse(responseCode = "404", description = "用户训练计划未找到")
    })
    @GetMapping("/{planId}")
    public ResponseEntity<PlanModel> get(@PathVariable("userId") Long userId, @PathVariable("planId") Long planId) {
        return ResponseEntity.ok(planModelAssembler.toModel(planService.get(userId, planId)));
    }

    @Operation(summary = "更新用户训练计划", description = "根据训练计划 ID 更新用户训练计划")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户训练计划"),
            @ApiResponse(responseCode = "404", description = "用户训练计划未找到")
    })
    @PutMapping("/{planId}")
    public ResponseEntity<PlanModel> update(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            @Valid @RequestBody final PlanInfoRequest info
    ) {
        return ResponseEntity.ok(planModelAssembler.toModel(planService.update(userId, planId, info)));
    }

    @Operation(summary = "删除用户训练计划", description = "根据训练计划 ID 删除用户训练计划")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除用户训练计划"),
            @ApiResponse(responseCode = "404", description = "用户训练计划未找到")
    })
    @DeleteMapping("/{planId}")
    public ResponseEntity<VoidModel> delete(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId
    ) {
        planService.delete(userId, planId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(PlanController.class).list(userId, null)).withRel("plans"))
        );
    }

    @Operation(summary = "完成用户训练计划", description = "根据训练计划 ID 完成用户训练计划")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功完成用户训练计划"),
            @ApiResponse(responseCode = "404", description = "用户训练计划未找到")
    })
    @PatchMapping("/{planId}/finish")
    public ResponseEntity<PlanModel> finish(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId
    ) {
        return ResponseEntity.ok(planModelAssembler.toModel(planService.finish(userId, planId)));
    }

}

package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.ExerciseModelAssembler;
import net.thmaster.hms.model.model.ExerciseModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.ExerciseInfoRequest;
import net.thmaster.hms.model.req.query.ExerciseQueryRequest;
import net.thmaster.hms.service.ExerciseService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 运动记录 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/plans/{planId}/exercises")
@Tag(name = "exercises", description = "运动记录")
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final ExerciseModelAssembler exerciseModelAssembler;

    @Operation(summary = "创建用户训练计划运动记录", description = "在指定用户训练计划下创建一条运动记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建训练计划运动记录")
    })
    @PostMapping
    public ResponseEntity<ExerciseModel> create(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            @Valid @RequestBody final ExerciseInfoRequest info
    ) {
        return ResponseEntity.ok(exerciseModelAssembler.toModel(exerciseService.save(userId, planId, info)));
    }

    @Operation(summary = "获取用户训练计划运动记录列表",
            description = "根据查询条件，获取用户训练计划符合条件的运动记录列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户训练计划运动记录列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<ExerciseModel>> list(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            final ExerciseQueryRequest query
    ) {
        return ResponseEntity.ok(exerciseModelAssembler.toCollectionModel(
                exerciseService.list(userId, planId, query),
                userId, planId, query
        ));
    }

    @Operation(summary = "获取用户训练计划运动记录", description = "根据运动记录 ID 获取用户训练计划运动记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户训练计划运动记录"),
            @ApiResponse(responseCode = "404", description = "用户训练计划运动记录未找到")
    })
    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseModel> get(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            @PathVariable("exerciseId") Long exerciseId
    ) {
        return ResponseEntity.ok(exerciseModelAssembler.toModel(exerciseService.get(userId, planId, exerciseId)));
    }

    @Operation(summary = "更新用户训练计划运动记录", description = "根据运动记录 ID 更新用户训练计划运动记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户训练计划运动记录"),
            @ApiResponse(responseCode = "404", description = "用户训练计划运动记录未找到")
    })
    @PutMapping("/{exerciseId}")
    public ResponseEntity<ExerciseModel> update(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            @PathVariable("exerciseId") Long exerciseId,
            @Valid @RequestBody final ExerciseInfoRequest info
    ) {
        return ResponseEntity.ok(exerciseModelAssembler.toModel(
                exerciseService.update(userId, planId, exerciseId, info)
        ));
    }

    @Operation(summary = "删除用户训练计划运动记录", description = "根据运动记录 ID 删除用户训练计划运动记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除用户训练计划运动记录"),
            @ApiResponse(responseCode = "404", description = "用户训练计划运动记录未找到")
    })
    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<VoidModel> delete(
            @PathVariable("userId") Long userId,
            @PathVariable("planId") Long planId,
            @PathVariable("exerciseId") Long exerciseId
    ) {
        exerciseService.delete(userId, planId, exerciseId);
        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(ExerciseController.class).list(userId, planId, null)).withSelfRel())
        );
    }

}

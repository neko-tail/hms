package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.WeightModelAssembler;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.model.WeightModel;
import net.thmaster.hms.model.req.WeightInfoRequest;
import net.thmaster.hms.model.req.query.WeightQueryRequest;
import net.thmaster.hms.service.WeightService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 体重记录 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/weights")
@Tag(name = "weights", description = "体重记录")
public class WeightController {

    private final WeightService weightService;

    private final WeightModelAssembler weightModelAssembler;

    @Operation(summary = "创建用户体重记录", description = "在指定用户下创建一条体重记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建用户体重记录")
    })
    @PostMapping
    public ResponseEntity<WeightModel> create(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final WeightInfoRequest info
    ) {
        return ResponseEntity.ok(weightModelAssembler.toModel(weightService.save(userId, info)));
    }

    @Operation(summary = "获取用户体重记录列表", description = "根据查询条件，获取用户符合条件的体重记录列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户体重记录列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<WeightModel>> list(
            @PathVariable("userId") Long userId,
            final WeightQueryRequest query
    ) {
        return ResponseEntity.ok(weightModelAssembler.toCollectionModel(
                weightService.list(userId, query),
                userId,
                query
        ));
    }

    @Operation(summary = "获取用户体重记录", description = "根据体重记录 ID 获取用户体重记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户体重记录"),
            @ApiResponse(responseCode = "404", description = "用户体重记录未找到")
    })
    @GetMapping("/{weightId}")
    public ResponseEntity<WeightModel> get(
            @PathVariable("userId") Long userId,
            @PathVariable("weightId") Long weightId
    ) {
        return ResponseEntity.ok(weightModelAssembler.toModel(weightService.get(userId, weightId)));
    }

    @Operation(summary = "更新用户体重记录", description = "根据体重记录 ID 更新用户体重记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户体重记录"),
            @ApiResponse(responseCode = "404", description = "用户体重记录未找到")
    })
    @PutMapping("/{weightId}")
    public ResponseEntity<WeightModel> update(
            @PathVariable("userId") Long userId,
            @PathVariable("weightId") Long weightId,
            @Valid @RequestBody final WeightInfoRequest info
    ) {
        return ResponseEntity.ok(weightModelAssembler.toModel(weightService.update(userId, weightId, info)));
    }

    @Operation(summary = "删除用户体重记录", description = "根据体重记录 ID 删除用户体重记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除用户体重记录"),
            @ApiResponse(responseCode = "404", description = "用户体重记录未找到")
    })
    @DeleteMapping("/{weightId}")
    public ResponseEntity<VoidModel> delete(
            @PathVariable("userId") Long userId,
            @PathVariable("weightId") Long weightId
    ) {
        weightService.delete(userId, weightId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(WeightController.class).list(userId, null)).withRel("weights"))
        );
    }

}

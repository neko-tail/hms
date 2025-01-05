package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.MovementModelAssembler;
import net.thmaster.hms.model.model.MovementModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.MovementInfoRequest;
import net.thmaster.hms.model.req.query.MovementQueryRequest;
import net.thmaster.hms.service.MovementService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 运动 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/movements")
@Tag(name = "movements", description = "运动")
public class MovementController {

    private final MovementService movementService;

    private final MovementModelAssembler movementModelAssembler;

    @Operation(summary = "创建运动", description = "创建一条运动")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建运动"),
    })
    @PostMapping
    public ResponseEntity<MovementModel> create(@Valid @RequestBody final MovementInfoRequest info) {
        return ResponseEntity.ok(movementModelAssembler.toModel(movementService.save(info)));
    }

    @Operation(summary = "获取运动列表", description = "根据查询参数获取运动列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取运动列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<MovementModel>> list(final MovementQueryRequest query) {
        return ResponseEntity.ok(movementModelAssembler.toCollectionModel(movementService.list(query), query));
    }

    @Operation(summary = "获取运动", description = "根据运动 ID 获取运动")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取运动"),
            @ApiResponse(responseCode = "404", description = "运动未找到")
    })
    @GetMapping("/{movementId}")
    public ResponseEntity<MovementModel> get(@PathVariable("movementId") Long movementId) {
        return ResponseEntity.ok(movementModelAssembler.toModel(movementService.get(movementId)));
    }

    @Operation(summary = "更新运动", description = "根据运动 ID 更新运动")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新运动"),
            @ApiResponse(responseCode = "404", description = "运动未找到")
    })
    @PutMapping("/{movementId}")
    public ResponseEntity<MovementModel> update(
            @PathVariable("movementId") Long movementId,
            @Valid @RequestBody final MovementInfoRequest info
    ) {
        return ResponseEntity.ok(movementModelAssembler.toModel(movementService.update(movementId, info)));
    }

    @Operation(summary = "删除运动", description = "根据运动 ID 删除运动")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除运动"),
            @ApiResponse(responseCode = "404", description = "运动未找到")
    })
    @DeleteMapping("/{movementId}")
    public ResponseEntity<VoidModel> delete(@PathVariable("movementId") Long movementId) {
        movementService.delete(movementId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(MovementController.class).list(null)).withRel("movements"))
        );
    }

}

package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.FoodModelAssembler;
import net.thmaster.hms.model.model.FoodModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.FoodInfoRequest;
import net.thmaster.hms.model.req.query.FoodQueryRequest;
import net.thmaster.hms.service.FoodService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 食物 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/foods")
@Tag(name = "foods", description = "食物")
public class FoodController {

    private final FoodService foodService;

    private final FoodModelAssembler foodModelAssembler;

    @Operation(summary = "创建食物", description = "创建一条食物")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建食物"),
    })
    @PostMapping
    public ResponseEntity<FoodModel> create(@Valid @RequestBody final FoodInfoRequest info) {
        return ResponseEntity.ok(foodModelAssembler.toModel(foodService.save(info)));
    }

    @Operation(summary = "获取食物列表", description = "根据查询参数获取食物列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取食物列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<FoodModel>> list(final FoodQueryRequest query) {
        return ResponseEntity.ok(foodModelAssembler.toCollectionModel(foodService.list(query), query));
    }

    @Operation(summary = "获取食物", description = "根据食物 ID 获取食物")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取食物"),
            @ApiResponse(responseCode = "404", description = "食物未找到")
    })
    @GetMapping("/{foodId}")
    public ResponseEntity<FoodModel> get(@PathVariable("foodId") Long foodId) {
        return ResponseEntity.ok(foodModelAssembler.toModel(foodService.get(foodId)));
    }

    @Operation(summary = "更新食物", description = "根据食物 ID 更新食物")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新食物"),
            @ApiResponse(responseCode = "404", description = "食物未找到")
    })
    @PutMapping("/{foodId}")
    public ResponseEntity<FoodModel> update(
            @PathVariable("foodId") Long foodId,
            @Valid @RequestBody final FoodInfoRequest info
    ) {
        return ResponseEntity.ok(foodModelAssembler.toModel(foodService.update(foodId, info)));
    }

    @Operation(summary = "删除食物", description = "根据食物 ID 删除食物")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除食物"),
            @ApiResponse(responseCode = "404", description = "食物未找到")
    })
    @DeleteMapping("/{foodId}")
    public ResponseEntity<VoidModel> delete(@PathVariable("foodId") Long foodId) {
        foodService.delete(foodId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(FoodController.class).list(null)).withRel("foods"))
        );
    }

}

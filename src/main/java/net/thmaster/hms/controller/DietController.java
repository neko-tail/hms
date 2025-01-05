package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.assembler.DietModelAssembler;
import net.thmaster.hms.model.dto.DietDTO;
import net.thmaster.hms.model.model.DietModel;
import net.thmaster.hms.model.model.VoidModel;
import net.thmaster.hms.model.req.DietInfoRequest;
import net.thmaster.hms.model.req.query.DietQueryRequest;
import net.thmaster.hms.service.DietService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * <p>
 * 饮食记录 前端控制器
 * </p>
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/diets")
@Tag(name = "diets", description = "饮食记录")
public class DietController {

    private final DietService dietService;

    private final DietModelAssembler dietModelAssembler;

    @Operation(summary = "创建用户饮食记录", description = "在指定用户下创建一条饮食记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "成功创建用户饮食记录")
    })
    @PostMapping
    public ResponseEntity<DietModel> create(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody final DietInfoRequest info
    ) {
        return ResponseEntity.ok(dietModelAssembler.toModel(dietService.save(userId, info)));
    }

    @Operation(summary = "获取用户饮食记录列表", description = "根据查询条件，获取用户符合条件的饮食记录列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户饮食记录列表")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<DietModel>> list(
            @PathVariable("userId") Long userId,
            final DietQueryRequest query
    ) {
        List<DietDTO> list = dietService.list(userId, query);

        return ResponseEntity.ok(dietModelAssembler.toCollectionModel(list, userId, query));
    }

    @Operation(summary = "获取用户饮食记录", description = "根据饮食记录 ID 获取用户饮食记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户饮食记录"),
            @ApiResponse(responseCode = "404", description = "用户饮食记录未找到")
    })
    @GetMapping("/{dietId}")
    public ResponseEntity<DietModel> get(
            @PathVariable("userId") Long userId,
            @PathVariable("dietId") Long dietId
    ) {
        return ResponseEntity.ok(dietModelAssembler.toModel(dietService.get(userId, dietId)));
    }

    @Operation(summary = "更新用户饮食记录", description = "根据饮食记录 ID 更新用户饮食记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新用户饮食记录"),
            @ApiResponse(responseCode = "404", description = "用户饮食记录未找到")
    })
    @PutMapping("/{dietId}")
    public ResponseEntity<DietModel> update(
            @PathVariable("userId") Long userId,
            @PathVariable("dietId") Long dietId,
            @Valid @RequestBody final DietInfoRequest info
    ) {
        return ResponseEntity.ok(dietModelAssembler.toModel(dietService.update(userId, dietId, info)));
    }

    @Operation(summary = "删除用户饮食记录", description = "根据饮食记录 ID 删除用户饮食记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除用户饮食记录"),
            @ApiResponse(responseCode = "404", description = "用户饮食记录未找到")
    })
    @DeleteMapping("/{dietId}")
    public ResponseEntity<VoidModel> delete(
            @PathVariable("userId") Long userId,
            @PathVariable("dietId") Long dietId
    ) {
        dietService.delete(userId, dietId);

        return ResponseEntity.ok(new VoidModel()
                .add(linkTo(methodOn(DietController.class).list(userId, null)).withRel("diets"))
        );
    }

}

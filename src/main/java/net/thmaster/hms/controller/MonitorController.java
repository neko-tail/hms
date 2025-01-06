package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.model.model.MonitorModel;
import net.thmaster.hms.service.MonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 用户状态监控
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/users/{userId}/monitor")
@Tag(name = "monitor", description = "用户状态监控")
public class MonitorController {

    private final MonitorService monitorService;

    @Operation(summary = "获取用户状态监控", description = "根据查询条件，获取用户状态监控")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户状态监控")
    })
    @GetMapping
    public ResponseEntity<MonitorModel> get(@PathVariable Long userId) {
        return ResponseEntity.ok(monitorService.get(userId)
                .add(linkTo(methodOn(MonitorController.class).get(userId)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).get(userId)).withRel("user"))
        );
    }

}

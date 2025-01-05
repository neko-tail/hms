package net.thmaster.hms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.thmaster.hms.model.model.TokenModel;
import net.thmaster.hms.model.req.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Token 控制器
 *
 * @author master
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hms/token")
@Tag(name = "token", description = "token controller")
public class TokenController {

    @Operation(summary = "用户登录，生成 token", description = "通过用户名和密码进行身份验证，生成 token 返回")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功",
                    content = {@Content(schema = @Schema(implementation = TokenModel.class))}),
            @ApiResponse(responseCode = "401", description = "无效的用户名或密码")
    })
    @PostMapping
    public ResponseEntity<TokenModel> token(@Valid @RequestBody final LoginRequest login) {
        return null;
    }
}

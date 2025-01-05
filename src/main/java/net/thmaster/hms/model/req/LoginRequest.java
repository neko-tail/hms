package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * 用户登录请求对象
 *
 * @author master
 */
@Schema(description = "用户登录请求对象")
public record LoginRequest(

        @NotBlank(message = "用户名不能为空")
        @Schema(description = "用户名")
        String username,

        @NotBlank(message = "密码不能为空")
        @Schema(description = "密码")
        String password

) {

}

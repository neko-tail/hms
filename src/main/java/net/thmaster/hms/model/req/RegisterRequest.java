package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * 用户注册请求对象
 *
 * @author master
 */
@Schema(description = "用户注册请求对象")
public record RegisterRequest(

        @NotBlank(message = "用户名不能为空")
        @Schema(description = "用户名")
        String username,

        @NotBlank(message = "密码不能为空")
        @Schema(description = "密码")
        String password,

        @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String email,

        @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String phoneNumber

) {
}

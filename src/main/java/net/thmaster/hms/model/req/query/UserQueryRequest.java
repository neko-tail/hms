package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户查询请求对象
 *
 * @author master
 */
@Schema(description = "用户查询请求对象")
public record UserQueryRequest(

        @Schema(description = "用户名，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String username,

        @Schema(description = "昵称，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String nickname,

        @Schema(description = "邮箱，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String email,

        @Schema(description = "手机号码，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String phoneNumber
) {

}

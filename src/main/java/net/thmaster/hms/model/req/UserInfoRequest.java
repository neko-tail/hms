package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户信息请求对象
 *
 * @author master
 */
@Schema(description = "用户信息请求对象")
public record UserInfoRequest(
        @Schema(description = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String nickName,

        @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String email,

        @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String phoneNumber,

        @Schema(description = "年龄", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer age,

        @Schema(description = "性别", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer gender,

        @Schema(description = "性别名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String genderName,

        @Schema(description = "身高", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double height,

        @Schema(description = "体重", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double weight
) {

}

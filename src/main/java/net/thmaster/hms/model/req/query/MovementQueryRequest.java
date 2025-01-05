package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 运动查询请求对象
 *
 * @author master
 */
@Schema(description = "运动查询请求对象")
public record MovementQueryRequest(

        @Schema(description = "运动名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String name

) {
}

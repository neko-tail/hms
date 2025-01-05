package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 食物查询请求对象
 *
 * @author master
 */
@Schema(description = "食物查询请求对象")
public record FoodQueryRequest(

        @Schema(description = "食物名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String name

) {
}

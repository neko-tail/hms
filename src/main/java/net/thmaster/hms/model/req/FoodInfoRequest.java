package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author master
 */
public record FoodInfoRequest(
        @NotNull
        @Schema(description = "名称")
        String name,

        @Schema(description = "介绍", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String desc,

        @NotNull
        @Schema(description = "热量")
        Double calorie,

        @Schema(description = "图片路径", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String img
) {

}

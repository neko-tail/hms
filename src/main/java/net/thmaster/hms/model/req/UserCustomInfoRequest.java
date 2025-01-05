package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author master
 */
public record UserCustomInfoRequest(
        @Schema(description = "每日热量限制", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double calorieLimit,

        @Schema(description = "初始体重", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double initWeight,

        @Schema(description = "目标体重", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Double targetWeight
) {

}

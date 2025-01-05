package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author master
 */
public record PlanInfoRequest(
        @NotNull
        @Schema(description = "计划名称")
        String name,

        @Schema(description = "计划描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String desc
) {

}

package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import net.thmaster.hms.enums.DietType;

import java.time.LocalDateTime;

/**
 * @author master
 */
public record DietInfoRequest(

        @NotNull
        @Schema(description = "饮食类型")
        DietType type,

        @NotNull
        @Schema(description = "食物 ID")
        Long foodId,

        @NotNull
        @Schema(description = "数量")
        Integer count,

        @Schema(description = "进食时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDateTime time
) {

}

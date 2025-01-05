package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author master
 */
public record ExerciseInfoRequest(
        @NotNull
        @Schema(description = "运动 ID")
        Long movementId,

        @NotNull
        @Schema(description = "次数")
        Integer count
) {

}

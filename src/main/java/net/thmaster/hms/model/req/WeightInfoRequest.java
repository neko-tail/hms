package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * @author master
 */
public record WeightInfoRequest(
        @NotNull
        @Schema(description = "体重")
        Double weight,

        @Schema(description = "日期", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate date
) {

}

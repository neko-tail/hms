package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author master
 */
public record BlogInfoRequest(
        @NotNull
        @Schema(description = "标题")
        String title,

        @Schema(description = "作者", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String author,

        @NotNull
        @Schema(description = "内容")
        String content

) {

}

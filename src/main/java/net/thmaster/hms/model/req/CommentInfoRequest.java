package net.thmaster.hms.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * @author master
 */
public record CommentInfoRequest(
        @NotNull
        @Schema(description = "内容")
        String content
) {

}

package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 博客查询请求对象
 *
 * @author master
 */
@Schema(description = "博客查询请求对象")
public record BlogQueryRequest(

        @Schema(description = "博客标题", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String title,
        @Schema(description = "博客作者", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String author

) {
}

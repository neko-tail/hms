package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 饮食记录查询请求对象
 *
 * @author master
 */
@Schema(description = "饮食记录查询请求对象")
public record DietQueryRequest(

        @Schema(description = "饮食类型名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String typeName,

        @Schema(description = "食物名称，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String foodName,

        @Schema(description = "日期，进食记录日期，指定此参数时，startTime 与 endTime 参数不生效",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate day,

        @Schema(description = "开始时间，进食时间范围开始", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDateTime startTime,

        @Schema(description = "结束时间，进食时间范围结束", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDateTime endTime
) {

}

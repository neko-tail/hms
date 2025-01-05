package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 训练计划查询请求对象
 *
 * @author master
 */
@Schema(description = "训练计划查询请求对象")
public record PlanQueryRequest(

        @Schema(description = "计划名称，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String name,

        @Schema(description = "计划描述，模糊查询", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String desc,

        @Schema(description = "日期，计划创建日期，指定此参数时，startTime 与 endTime 参数不生效",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate day,

        @Schema(description = "开始时间，计划创建时间范围开始", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDateTime startTime,

        @Schema(description = "结束时间，计划创建时间范围结束", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDateTime endTime,

        @Schema(description = "已完成，计划是否已完成", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean fin

) {

}

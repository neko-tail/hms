package net.thmaster.hms.model.req.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * 体重记录查询请求对象
 *
 * @author master
 */
public record WeightQueryRequest(

        @Schema(description = "日期，体重记录日期，指定此参数时，startTime 与 endTime 参数不生效",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate day,

        @Schema(description = "开始时间，体重记录时间范围开始", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate startTime,

        @Schema(description = "结束时间，体重记录时间范围结束", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        LocalDate endTime

) {
}

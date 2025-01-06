package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 用户状态监控资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户状态监控资源模型")
public class MonitorModel extends RepresentationModel<MonitorModel> {

    private Long userId;

    @Schema(description = "初始体重")
    private Double initWeight;

    @Schema(description = "目标体重")
    private Double targetWeight;

    @Schema(description = "当前体重")
    private Double currentWeight;

    @Schema(description = "已减去体重")
    private Double lostWeight;

    @Schema(description = "热量限制")
    private Double caloriesLimit;

    @Schema(description = "摄入热量")
    private Double intakesCalories;

    @Schema(description = "消耗热量")
    private Double burnsCalories;

    @Schema(description = "剩余热量")
    private Double remainingCalories;
}

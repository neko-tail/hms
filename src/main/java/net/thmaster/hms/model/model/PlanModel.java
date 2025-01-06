package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * 训练计划资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "训练计划资源模型")
public class PlanModel extends RepresentationModel<PlanModel> {

    private Long id;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "计划名称")
    private String name;

    @Schema(description = "计划描述")
    private String desc;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "完成时间")
    private LocalDateTime finTime;

    @Schema(description = "已完成")
    private Boolean fin;

}

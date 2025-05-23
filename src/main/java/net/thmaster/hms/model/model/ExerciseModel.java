package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 运动记录资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "运动记录资源模型")
public class ExerciseModel extends RepresentationModel<ExerciseModel> {

    private Long id;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "运动名称")
    private String movementName;

    @Schema(description = "次数")
    private String count;

    @Schema(description = "重量")
    private String weight;
}

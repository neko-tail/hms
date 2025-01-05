package net.thmaster.hms.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thmaster.hms.model.entity.Movement;

/**
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    private Long id;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "计划 ID")
    private Long planId;

    @Schema(description = "运动")
    private Movement movement;

    @Schema(description = "次数")
    private Integer count;

}

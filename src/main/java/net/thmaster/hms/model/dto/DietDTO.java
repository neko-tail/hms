package net.thmaster.hms.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thmaster.hms.enums.DietType;
import net.thmaster.hms.model.entity.Food;

import java.time.LocalDateTime;

/**
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {

    private Long id;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "饮食类型")
    private DietType type;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "进食时间")
    private LocalDateTime time;

    @Schema(description = "食物")
    private Food food;

}

package net.thmaster.hms.model.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * 饮食记录资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "饮食记录资源模型")
public class DietModel extends RepresentationModel<DietModel> {

    private Long id;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "饮食类型名称")
    private String typeName;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "进食时间")
    private LocalDateTime time;

    @Schema(description = "食物名称")
    private String foodName;
}

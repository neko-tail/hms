package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 食物资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "食物资源模型")
public class FoodModel extends RepresentationModel<FoodModel> {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "介绍")
    private String desc;

    @Schema(description = "热量")
    private Double calorie;

    @Schema(description = "图片路径")
    private String img;
}

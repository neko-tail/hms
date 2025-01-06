package net.thmaster.hms.model.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 用户自定义数据资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户自定义数据资源模型")
public class UserCustomModel extends RepresentationModel<UserCustomModel> {

    @Schema(description = "用户 ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "每日热量限制")
    private Double calorieLimit;

    @Schema(description = "初始体重")
    private Double initWeight;

    @Schema(description = "目标体重")
    private Double targetWeight;
}

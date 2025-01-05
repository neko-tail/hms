package net.thmaster.hms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户自定义数据
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_custom")
@Schema(name = "UserCustom", description = "用户自定义数据")
public class UserCustom {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "每日热量限制")
    @TableField("calorie_limit")
    private Double calorieLimit;

    @Schema(description = "初始体重")
    @TableField("init_weight")
    private Double initWeight;

    @Schema(description = "目标体重")
    @TableField("target_weight")
    private Double targetWeight;
}

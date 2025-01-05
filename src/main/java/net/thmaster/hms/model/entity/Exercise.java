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
 * 运动记录
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("exercise")
@Schema(name = "Exercise", description = "运动记录")
public class Exercise {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "计划 ID")
    @TableField("plan_id")
    private Long planId;

    @Schema(description = "运动 ID")
    @TableField("movement_id")
    private Long movementId;

    @Schema(description = "次数")
    @TableField("count")
    private Integer count;
}

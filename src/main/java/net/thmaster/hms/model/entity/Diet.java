package net.thmaster.hms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.thmaster.hms.enums.DietType;

import java.time.LocalDateTime;

/**
 * <p>
 * 饮食记录
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("diet")
@Schema(name = "Diet", description = "饮食记录")
public class Diet {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "饮食类型")
    @TableField("type")
    private DietType type;

    @Schema(description = "食物 ID")
    @TableField("food_id")
    private Long foodId;

    @Schema(description = "数量")
    @TableField("count")
    private Integer count;

    @Schema(description = "进食时间")
    @TableField("time")
    private LocalDateTime time;
}

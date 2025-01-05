package net.thmaster.hms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 训练计划
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("plan")
@Schema(name = "Plan", description = "训练计划")
public class Plan {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "计划名称")
    @TableField("name")
    private String name;

    @Schema(description = "计划描述")
    @TableField("`desc`")
    private String desc;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "完成时间")
    @TableField("fin_time")
    private LocalDateTime finTime;

    @Schema(description = "已完成")
    @TableField("fin")
    private boolean fin;
}

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
 * 运动
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("movement")
@Schema(name = "Movement", description = "运动")
public class Movement {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "介绍")
    @TableField("`desc`")
    private String desc;

    @Schema(description = "热量")
    @TableField("calorie")
    private Double calorie;

    @Schema(description = "图片路径")
    @TableField("img")
    private String img;
}

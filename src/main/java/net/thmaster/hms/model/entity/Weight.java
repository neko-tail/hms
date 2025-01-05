package net.thmaster.hms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 * 体重记录
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("weight")
@Schema(name = "Weight", description = "体重记录")
public class Weight {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID ")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "体重")
    @TableField("weight")
    private Double weight;

    @Schema(description = "日期")
    @TableField("date")
    private LocalDate date;
}

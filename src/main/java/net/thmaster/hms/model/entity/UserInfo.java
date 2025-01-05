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
 * 用户信息
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_info")
@Schema(name = "UserInfo", description = "用户信息")
public class UserInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "年龄")
    @TableField("age")
    private Integer age;

    @Schema(description = "性别, 0 未知, 1 男, 2 女，3 自定义")
    @TableField("gender")
    private Integer gender;

    @Schema(description = "性别名称", example = "阿帕奇武装直升机")
    @TableField("gender_name")
    private String genderName;

    @Schema(description = "身高")
    @TableField("height")
    private Double height;

    @Schema(description = "体重")
    @TableField("weight")
    private Double weight;
}

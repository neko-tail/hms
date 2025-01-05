package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 用户资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户资源模型")
public class UserModel extends RepresentationModel<UserModel> {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phoneNumber;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "性别名称")
    private String genderName;

    @Schema(description = "身高")
    private Double height;

    @Schema(description = "体重")
    private Double weight;
}

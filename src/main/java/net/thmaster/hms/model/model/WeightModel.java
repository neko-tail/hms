package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

/**
 * 体重记录资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "体重记录资源模型")
public class WeightModel extends RepresentationModel<WeightModel> {

    private Long id;

    @Schema(description = "用户 ID ")
    private Long userId;

    @Schema(description = "体重")
    private Double weight;

    @Schema(description = "日期")
    private LocalDate date;

}

package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

/**
 * 空响应资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "空响应资源模型")
public class VoidModel extends RepresentationModel<VoidModel> {

}

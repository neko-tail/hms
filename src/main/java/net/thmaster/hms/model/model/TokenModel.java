package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * token 资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "token 资源模型")
public class TokenModel extends RepresentationModel<TokenModel> {
}

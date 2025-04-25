package net.thmaster.hms.model.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

/**
 * 评论资源模型
 *
 * @author master
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "评论资源模型")
public class CommentModel extends RepresentationModel<CommentModel> {

    private Long id;

    @Schema(description = "博客 ID")
    private Long blogId;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "内容")
    private String content;

}

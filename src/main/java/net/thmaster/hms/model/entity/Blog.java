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
 * 博客
 * </p>
 *
 * @author master
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog")
@Schema(name = "Blog", description = "博客")
public class Blog {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "标题")
    @TableField("title")
    private String title;

    @Schema(description = "作者")
    @TableField("`author`")
    private String author;

    @Schema(description = "内容")
    @TableField("`content`")
    private String content;

}

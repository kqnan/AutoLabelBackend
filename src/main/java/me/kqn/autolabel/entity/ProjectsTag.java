package me.kqn.autolabel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-18
 */
@Getter
@Setter
@ToString
@TableName("projects_tag")
@ApiModel(value = "ProjectsTag对象", description = "")
public class ProjectsTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("text")
    private String text;

    @TableField("project_id")
    private Integer projectId;


}

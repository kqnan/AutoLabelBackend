package me.kqn.autolabel.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.kqn.autolabel.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
@TableName("projects_project")
@ApiModel(value = "ProjectsProject对象", description = "")
public class ProjectsProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("guideline")
    private String guideline;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("project_type")
    private String projectType;

    @TableField("random_order")
    private Boolean randomOrder;

    @TableField("collaborative_annotation")
    private Boolean collaborativeAnnotation;

    @TableField("polymorphic_ctype_id")
    private Integer polymorphicCtypeId;

    @TableField("single_class_classification")
    private Boolean singleClassClassification;

    @TableField("created_by_id")
    private Integer createdById;


}

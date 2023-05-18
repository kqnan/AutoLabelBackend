package me.kqn.autolabel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@TableName("projects_member")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectsMember对象", description = "")
public class ProjectsMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("project_id")
    private Integer projectId;

    @TableField("role_id")
    private Integer roleId;

    @TableField("user_id")
    private Integer userId;


}

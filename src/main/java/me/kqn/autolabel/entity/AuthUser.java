package me.kqn.autolabel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-05-17
 */
@Getter
@Setter
@TableName("auth_user")
@ToString
@ApiModel(value = "AuthUser对象", description = "")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("password")
    private String password;

    @TableField("last_login")
    private LocalDateTime lastLogin;

    @TableField("is_superuser")
    private Boolean isSuperuser;

    @TableField("username")
    private String username;

    @TableField("first_name")
    private String firstName;

    @TableField("last_name")
    private String lastName;

    @TableField("email")
    private String email;

    @TableField("is_staff")
    private Boolean isStaff;

    @TableField("is_active")
    private Boolean isActive;

    @TableField("date_joined")
    private LocalDateTime dateJoined;


}

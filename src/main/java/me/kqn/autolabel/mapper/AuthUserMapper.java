package me.kqn.autolabel.mapper;

import me.kqn.autolabel.entity.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-17
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}

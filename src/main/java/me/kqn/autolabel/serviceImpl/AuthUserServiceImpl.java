package me.kqn.autolabel.serviceImpl;

import me.kqn.autolabel.entity.AuthUser;
import me.kqn.autolabel.mapper.AuthUserMapper;
import me.kqn.autolabel.service.AuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-17
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

}

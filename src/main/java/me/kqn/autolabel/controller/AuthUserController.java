package me.kqn.autolabel.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.kqn.autolabel.JSONResult;
import me.kqn.autolabel.entity.AuthUser;
import me.kqn.autolabel.mapper.AuthUserMapper;
import me.kqn.autolabel.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kurt_kong
 * @since 2023-05-17
 */
@Controller
@RequestMapping("/v1/auth/")
public class AuthUserController {

    // 测试登录，浏览器访问： http://localhost:8081/v1/auth/doLogin?username=zhang&password=123456
    @Autowired
    public AuthUserMapper mapper;
    @RequestMapping("/login/")
    public void doLogin(@RequestBody String data, HttpServletResponse response) throws IOException {
        JSONObject jsonObject=JSONObject.parseObject(data);
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        QueryWrapper<AuthUser> qw= new QueryWrapper<AuthUser>().eq("username", username);
        AuthUser authUser=mapper.selectOne(qw);
        if(authUser!=null) {
            StpUtil.login(authUser.getId());
            response.setStatus(200);
            response.getWriter().print("{\"key\": \"\"}");
            return;
        }
        response.setStatus(400);
        response.getWriter().print("{\"non_field_errors\":[\"Unable to log in with provided credentials.\"]}");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}

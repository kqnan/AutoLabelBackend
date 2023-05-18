package me.kqn.autolabel.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.kqn.autolabel.entity.AuthUser;
import me.kqn.autolabel.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    AuthUserMapper authUserMapper;
    @GetMapping("/v1/me")
    public void me(HttpServletResponse response) throws IOException {
        StpUtil.checkLogin();
        AuthUser authUser= authUserMapper.selectOne(new QueryWrapper<AuthUser>().eq("id",StpUtil.getLoginId()));
        response.getWriter().print("{\"id\":%s,\"username\":\"%s\",\"is_superuser\":%s,\"is_staff\":%s}".formatted(authUser.getId(),authUser.getUsername(),authUser.getIsSuperuser(),authUser.getIsStaff()));
    }
}

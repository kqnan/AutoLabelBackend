package me.kqn.autolabel;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi //开启Swagger
@MapperScan("me.kqn.autolabel.mapper")
public class AutoLabelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoLabelApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}

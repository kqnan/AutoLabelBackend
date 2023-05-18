package me.kqn.autolabel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CodeGenerator {
    /**
     * 代码生成器
     */


    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
//        tables.add("BussinessInfo");
//        tables.add("ClientContract");
//        tables.add("ClientInfo");
//        tables.add("EntityInfo");
//        tables.add("GuarantorCircle");
//        tables.add("GuarantorInfo");
//        tables.add("LoanInfo");
//        tables.add("GuarantortypeInfo");
//        tables.add("EnterpriseInfo");
//        tables.add("Shareholder");
//        tables.add("MortgagePledge");
//        tables.add("EnterpriseImageInfo");
//        tables.add("EnterpriseBussinessImage");
        tables.add("roles_role");
//        tables.add("BlackListInfo");
//        tables.add("LoanRelaImageInfo");
//        tables.add("RepaymentPlan");
        /**
         * 以下为数据接口导入原生数据
         */
//        tables.add("szdy_cmd_bzrxxb");
//        tables.add("szdy_cmd_dkghqd");
//        tables.add("szdy_cmd_dwdbxxb");
//        tables.add("szdy_cmd_hqckzhyyb");
//        tables.add("szdy_cmd_khxxb");
//        tables.add("szdy_cmd_ywxxxxb");


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/autolabel?useUnicode=true&useSSL=true&characterEncoding=utf8", "root", "84769629a")
                .globalConfig(builder -> {
                    builder.author("kurt_kong")               //作者
                            .outputDir(System.getProperty("user.dir") + "\\test")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();

                })
                .packageConfig(builder -> {
                    builder.parent("me.kqn.autolabel")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix("p_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
//                            .controllerBuilder()
//                            .formatFileName("%sController")
//                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}


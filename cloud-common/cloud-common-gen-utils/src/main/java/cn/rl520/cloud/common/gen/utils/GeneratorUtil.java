package cn.rl520.cloud.common.gen.utils;

import cn.rl520.cloud.common.gen.utils.domain.DataSource;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Collections;

/**
 * @Author wenbo
 * @Date 2024/1/8 11:21
 */
public class GeneratorUtil {


    public static String getProjectPath(){
        return  System.getProperty("user.dir");
    }

    public static void generateFile(DataSource dataSource, String module, String[] tableNames, String author, String parentPackageName, String modulePackageName){
        String modulePath = module!=null?getProjectPath()+"/"+module+"/":getProjectPath()+"/";
        // 代码生成器
        FastEcareAutoGenerator.create(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword())
                .globalConfig(builder -> builder.outputDir(modulePath+"src/main/java")
                        .author(author)
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd")
                        .build())
                .packageConfig(builder -> builder.parent(parentPackageName)
                        .moduleName(modulePackageName)
                        .entity("domain")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, modulePath+"src/main/resources/mapper"))
                        .build())
                .strategyConfig(builder -> builder.enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        .addInclude(tableNames)
                        .entityBuilder().enableLombok().enableFileOverride().logicDeleteColumnName("delFlag").addIgnoreColumns("tenant_id", "create_dept","create_by", "create_time", "update_by", "update_time")
                        .serviceBuilder().enableFileOverride()
                        .mapperBuilder().enableFileOverride().enableBaseResultMap().enableBaseColumnList()
                        .controllerBuilder().enableFileOverride().enableRestStyle()
                        .build())
                .execute();
    }


    public static void generatorCode(String module,String parentPackageName){
        // 查询表信息
        String modulePath = module!=null?getProjectPath()+"/"+module+"/":getProjectPath()+"/";
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/ecare-cloud","root","ruili425714")
                .globalConfig(builder -> builder.outputDir(modulePath+"src/main/java")
                        .author("wwb")
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd")
                        .build())
                .packageConfig(builder -> builder.parent(parentPackageName)
                        .entity("domain")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, modulePath+"src/main/resources/mapper"))
                        .build())
                .strategyConfig(builder -> builder.enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        .addInclude("sys_uer")
                        .build())
                .execute();
    }

    public static void main(String[] args) {
        String modulePath = getProjectPath()+"/cloud-common/cloud-common-gen-utils";
        String parentPackageName = "cn.echase.cloud.common.gen.utils";
        //generatorCode("cloud-common/cloud-common-gen-utils","cn.echase.cloud.common.gen.utils");
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/ecare-cloud","root","ruili425714").build();
        PackageConfig packageInfo = new PackageConfig.Builder().parent(parentPackageName)
                .entity("domain")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .pathInfo(Collections.singletonMap(OutputFile.xml, modulePath+"/src/main/resources/mapper")).build();
        TemplateConfig template = new TemplateConfig.Builder().build();
        StrategyConfig strategyConfig = new StrategyConfig.Builder().enableCapitalMode().enableSkipView()
                .disableSqlFilter().addInclude("sys_user")
                .entityBuilder().enableLombok().enableFileOverride()
                .controllerBuilder().enableRestStyle().build();
        GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(modulePath+"/src/main/java").author("wwb")
                .disableOpenDir()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd").build();
        InjectionConfig injection = new InjectionConfig.Builder().build();
        EcareAutoGenerator ecareAutoGenerator = new EcareAutoGenerator(dataSourceConfig).packageInfo(packageInfo)
                .template(template).strategy(strategyConfig).global(globalConfig).injection(injection).template(new TemplateConfig.Builder().build());
        ecareAutoGenerator.execute();
//        if (null == ecareAutoGenerator.config) {
//            ecareAutoGenerator.config = new ConfigBuilder(packageInfo, dataSourceConfig, strategyConfig, template, globalConfig, injection);
//        }
//        List<TableInfo> tableInfos = ecareAutoGenerator.config.getTableInfoList();
//        tableInfos.forEach(tableInfo -> {
//            System.out.println(JSONUtil.toJsonStr(tableInfo));
//        });
//        ecareAutoGenerator.execute();
//        System.out.println(JSONUtil.toJsonStr(ecareAutoGenerator.config.getDataSourceConfig()));
    }



}

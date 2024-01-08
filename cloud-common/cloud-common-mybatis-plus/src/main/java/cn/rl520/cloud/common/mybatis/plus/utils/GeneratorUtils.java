package cn.rl520.cloud.common.mybatis.plus.utils;

import cn.echase.cloud.common.core.domain.TenantEntity;
import cn.echase.cloud.common.core.utils.StringUtils;
import cn.rl520.cloud.common.mybatis.plus.domain.DataSource;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Scanner;

/**
 * @Author wenbo
 * @Date 2024/1/2 23:31
 */
@RequiredArgsConstructor
public class GeneratorUtils {

    public static String getProjectPath(){
        return  System.getProperty("user.dir");
    }

    public static void generateFile(DataSource dataSource,String module,String[] tableNames,String author,String parentPackageName,String modulePackageName){
        String modulePath = module!=null?getProjectPath()+"/"+module+"/":getProjectPath()+"/";
        // 代码生成器
        FastAutoGenerator.create(dataSource.getUrl(),dataSource.getUserName(),dataSource.getPassword())
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
                        .entityBuilder().superClass(TenantEntity.class).enableLombok().enableFileOverride().logicDeleteColumnName("delFlag").addIgnoreColumns("tenant_id", "create_dept","create_by", "create_time", "update_by", "update_time")
                        .serviceBuilder().enableFileOverride()
                        .mapperBuilder().enableFileOverride().superClass("cn.echase.cloud.common.mybatis.plus.mapper.BaseMapperPlus").enableBaseResultMap().enableBaseColumnList()
                        .controllerBuilder().enableFileOverride()
                        .build())
                .execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入表命" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }




}

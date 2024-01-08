package cn.rl520.cloud.common.gen.utils.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

/**
 * @Author wenbo
 * @Date 2024/1/8 14:11
 */
public abstract class EcareAbstractTemplateEngine extends AbstractTemplateEngine {

    public abstract @NotNull EcareAbstractTemplateEngine init(@NotNull ConfigBuilder configBuilder);

    @Override
    public @NotNull AbstractTemplateEngine setConfigBuilder(@NotNull ConfigBuilder configBuilder) {
        return super.setConfigBuilder(configBuilder);
    }

    @Override
    public @NotNull AbstractTemplateEngine batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = config.getTableInfoList();
            tableInfoList.forEach((tableInfo) -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
                Optional.ofNullable(config.getInjectionConfig()).ifPresent((t) -> {
                    t.beforeOutputFile(tableInfo, objectMap);
                    this.outputCustomFile(t.getCustomFiles(), tableInfo, objectMap);
                });
                this.outputEntity(tableInfo, objectMap);
                this.outputMapper(tableInfo, objectMap);
                this.outputVo(tableInfo, objectMap);
                this.outputService(tableInfo, objectMap);
                this.outputController(tableInfo, objectMap);
            });
            return this;
        } catch (Exception var3) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", var3);
        }
        //return super.batchOutput();
    }

    public EcareAbstractTemplateEngine() {
        super();
    }

    @Override
    protected void outputEntity(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = this.getPathInfo(OutputFile.entity)+"/entity";
        if (StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityPath)) {
            this.getTemplateFilePath((template) -> {
                return template.getEntity(this.getConfigBuilder().getGlobalConfig().isKotlin());
            }).ifPresent((entity) -> {
                String entityFile = String.format(entityPath + File.separator + "%s" + this.suffixJavaOrKt(), entityName);
                this.outputFile(this.getOutputFile(entityFile, OutputFile.entity), objectMap, entity, this.getConfigBuilder().getStrategyConfig().entity().isFileOverride());
            });
        }
    }

    protected void outputVo(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName()+"Vo";
        String entityPath = this.getPathInfo(OutputFile.entity)+"/vo";
        if (StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityPath)) {
            this.getTemplateFilePath((template) -> {
                return template.getEntity(this.getConfigBuilder().getGlobalConfig().isKotlin());
            }).ifPresent((entity) -> {
                String entityFile = String.format(entityPath + File.separator + "%s" + this.suffixJavaOrKt(), entityName);
                this.outputFile(this.getOutputFile(entityFile, OutputFile.entity), objectMap, "/templates/vm/java/vo.java.vm", this.getConfigBuilder().getStrategyConfig().entity().isFileOverride());
            });
        }
    }

    @Override
    protected void outputFile(@NotNull File file, @NotNull Map<String, Object> objectMap, @NotNull String templatePath, boolean fileOverride) {
        if (this.isCreate(file, fileOverride)) {
            try {
                boolean exist = file.exists();
                if (!exist) {
                    File parentFile = file.getParentFile();
                    FileUtils.forceMkdir(parentFile);
                }

                this.writer(objectMap, templatePath, file);
            } catch (Exception var7) {
                throw new RuntimeException(var7);
            }
        }
    }

    @Override
    public @NotNull Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
        StrategyConfig strategyConfig = config.getStrategyConfig();
        Map<String, Object> controllerData = strategyConfig.controller().renderData(tableInfo);
        Map<String, Object> objectMap = new HashMap(controllerData);
        Map<String, Object> mapperData = strategyConfig.mapper().renderData(tableInfo);
        objectMap.putAll(mapperData);
        Map<String, Object> serviceData = strategyConfig.service().renderData(tableInfo);
        objectMap.putAll(serviceData);
        Map<String, Object> entityData = strategyConfig.entity().renderData(tableInfo);
        objectMap.putAll(entityData);
        objectMap.put("config", config);
        objectMap.put("package", config.getPackageConfig().getPackageInfo());
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("kotlin", globalConfig.isKotlin());
        objectMap.put("swagger", globalConfig.isSwagger());
        objectMap.put("springdoc", globalConfig.isSpringdoc());
        objectMap.put("date", globalConfig.getCommentDate());
        String schemaName = "";
        if (strategyConfig.isEnableSchema()) {
            schemaName = config.getDataSourceConfig().getSchemaName();
            if (StringUtils.isNotBlank(schemaName)) {
                schemaName = schemaName + ".";
                tableInfo.setConvert(true);
            }
        }

        objectMap.put("schemaName", schemaName);
        objectMap.put("table", tableInfo);
        objectMap.put("Entity", tableInfo.getEntityName());
        objectMap.put("entity", tableInfo.getEntityName().substring(0,1).toLowerCase(Locale.ROOT)+tableInfo.getEntityName().substring(1));
        objectMap.put("Vo", tableInfo.getEntityName()+"Vo");
        objectMap.put("vo", tableInfo.getEntityName().substring(0,1).toLowerCase(Locale.ROOT)+tableInfo.getEntityName().substring(1)+"Vo");
        return objectMap;
    }
}

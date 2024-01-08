package cn.rl520.cloud.common.gen.utils;

import cn.rl520.cloud.common.gen.utils.config.EcareTemplateConfig;
import cn.rl520.cloud.common.gen.utils.engine.EcareAbstractTemplateEngine;
import cn.rl520.cloud.common.gen.utils.engine.EcareVelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Author wenbo
 * @Date 2024/1/8 12:22
 *对mybatisplus.generator自动生成方法进行重写操作
 */
@Slf4j
public class EcareAutoGenerator extends AutoGenerator {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 配置信息
     */
    protected ConfigBuilder config;
    /**
     * 注入配置
     */
    protected InjectionConfig injection;
    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;

    private EcareTemplateConfig ecareTemplateConfig = new EcareTemplateConfig.Builder().build();

    private TemplateConfig templateConfig;

    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;


    public EcareAutoGenerator(@NotNull DataSourceConfig dataSourceConfig) {
        super(dataSourceConfig);
        this.dataSource = dataSourceConfig;
    }

    @Override
    public EcareAutoGenerator injection(@NotNull InjectionConfig injectionConfig) {
        super.injection(injectionConfig);
        this.injection = injectionConfig;
        return this;
    }

    @Override
    public EcareAutoGenerator strategy(@NotNull StrategyConfig strategyConfig) {
        super.strategy(strategyConfig);
        this.strategy = strategyConfig;
        return this;
    }

    @Override
    public EcareAutoGenerator packageInfo(@NotNull PackageConfig packageConfig) {
        super.packageInfo(packageConfig);
        this.packageInfo = packageConfig;
        return this;
    }

    @Override
    public EcareAutoGenerator template(@NotNull TemplateConfig templateConfig) {
        super.template(templateConfig);
        templateConfig = new TemplateConfig.Builder()
                .entity(ecareTemplateConfig.getEntity(false))
                .service(ecareTemplateConfig.getService())
                .serviceImpl(ecareTemplateConfig.getServiceImpl())
                .mapper(ecareTemplateConfig.getMapper())
                .xml(ecareTemplateConfig.getXml())
                .controller(ecareTemplateConfig.getController())
                .build();
        this.templateConfig = templateConfig;
        return this;
    }

    @Override
    public EcareAutoGenerator global(@NotNull GlobalConfig globalConfig) {
        super.global(globalConfig);
        this.globalConfig = globalConfig;
        return this;
    }

    @Override
    public EcareAutoGenerator config(@NotNull ConfigBuilder configBuilder) {
        super.config(configBuilder);
        this.config = configBuilder;
        return this;
    }

    /**
     * 生成代码
     */
    public void execute() {
        this.execute(null);
    }



    public void execute(EcareAbstractTemplateEngine templateEngine) {
        log.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, templateConfig, globalConfig, injection);
        }
        if (null == templateEngine) {
            // 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
            templateEngine = new EcareVelocityTemplateEngine();
        }
        templateEngine.setConfigBuilder(config);
        // 模板引擎初始化执行文件输出
        templateEngine.init(config).batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

    /**
     * 开放表信息、预留子类重写
     *
     * @param config 配置信息
     * @return ignore
     */
    @NotNull
    protected List<TableInfo> getAllTableInfoList(@NotNull ConfigBuilder config) {
        return config.getTableInfoList();
    }


    public static void map(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

}

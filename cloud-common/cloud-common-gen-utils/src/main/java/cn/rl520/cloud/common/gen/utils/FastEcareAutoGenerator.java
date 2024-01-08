package cn.rl520.cloud.common.gen.utils;

import cn.rl520.cloud.common.gen.utils.engine.EcareAbstractTemplateEngine;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.*;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author wenbo
 * @Date 2024/1/8 19:41
 */
public class FastEcareAutoGenerator {

    private final DataSourceConfig.Builder dataSourceConfigBuilder;
    private final GlobalConfig.Builder globalConfigBuilder;
    private final PackageConfig.Builder packageConfigBuilder;
    private final StrategyConfig.Builder strategyConfigBuilder;
    private final InjectionConfig.Builder injectionConfigBuilder;
    private final TemplateConfig.Builder templateConfigBuilder;
    private EcareAbstractTemplateEngine templateEngine;
    private final Scanner scanner;

    private FastEcareAutoGenerator(DataSourceConfig.Builder dataSourceConfigBuilder) {
        this.scanner = new Scanner(System.in);
        this.dataSourceConfigBuilder = dataSourceConfigBuilder;
        this.globalConfigBuilder = new GlobalConfig.Builder();
        this.packageConfigBuilder = new PackageConfig.Builder();
        this.strategyConfigBuilder = new StrategyConfig.Builder();
        this.injectionConfigBuilder = new InjectionConfig.Builder();
        this.templateConfigBuilder = new TemplateConfig.Builder();
    }

    public static FastEcareAutoGenerator create(@NotNull String url, String username, String password) {
        return new FastEcareAutoGenerator(new DataSourceConfig.Builder(url, username, password));
    }

    public static FastEcareAutoGenerator create(@NotNull DataSourceConfig.Builder dataSourceConfigBuilder) {
        return new FastEcareAutoGenerator(dataSourceConfigBuilder);
    }

    public String scannerNext(String message) {
        System.out.println(message);
        String nextLine = this.scanner.nextLine();
        return StringUtils.isBlank(nextLine) ? this.scanner.next() : nextLine;
    }

    public FastEcareAutoGenerator dataSourceConfig(Consumer<DataSourceConfig.Builder> consumer) {
        consumer.accept(this.dataSourceConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator dataSourceConfig(BiConsumer<Function<String, String>, DataSourceConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.dataSourceConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator globalConfig(Consumer<GlobalConfig.Builder> consumer) {
        consumer.accept(this.globalConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator globalConfig(BiConsumer<Function<String, String>, GlobalConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.globalConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator packageConfig(Consumer<PackageConfig.Builder> consumer) {
        consumer.accept(this.packageConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator packageConfig(BiConsumer<Function<String, String>, PackageConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.packageConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator strategyConfig(Consumer<StrategyConfig.Builder> consumer) {
        consumer.accept(this.strategyConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator strategyConfig(BiConsumer<Function<String, String>, StrategyConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.strategyConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator injectionConfig(Consumer<InjectionConfig.Builder> consumer) {
        consumer.accept(this.injectionConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator injectionConfig(BiConsumer<Function<String, String>, InjectionConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.injectionConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator templateConfig(Consumer<TemplateConfig.Builder> consumer) {
        consumer.accept(this.templateConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator templateConfig(BiConsumer<Function<String, String>, TemplateConfig.Builder> biConsumer) {
        biConsumer.accept(this::scannerNext, this.templateConfigBuilder);
        return this;
    }

    public FastEcareAutoGenerator templateEngine(EcareAbstractTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }

    public void execute() {
        (new EcareAutoGenerator(this.dataSourceConfigBuilder.build())).global(this.globalConfigBuilder.build()).packageInfo(this.packageConfigBuilder.build()).strategy(this.strategyConfigBuilder.build()).injection(this.injectionConfigBuilder.build()).template(this.templateConfigBuilder.build()).execute(this.templateEngine);
    }

}

package cn.rl520.cloud.common.gen.utils.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @Author wenbo
 * @Date 2024/1/8 15:11
 */
@Slf4j
public class EcareTemplateConfig {
    private String entity;
    private String entityKt;
    private String controller;
    private String mapper;
    private String xml;
    private String service;
    private String serviceImpl;
    private boolean disableEntity;

    private EcareTemplateConfig() {
        this.entity = "/templates/vm/java/domain.java";
        this.entityKt = "/templates/vm/java/entity.kt";
        this.controller = "/templates/vm/java/controller.java";
        this.mapper = "/templates/vm/java/mapper.java";
        this.xml = "/templates/vm/xml/mapper.xml";
        this.service = "/templates/vm/java/service.java";
        this.serviceImpl = "/templates/vm/java/serviceImpl.java";
    }

    private void logger(String value, TemplateType templateType) {
        if (StringUtils.isBlank(value)) {
            log.warn("推荐使用disable(TemplateType.{})方法进行默认模板禁用.", templateType.name());
        }

    }

    public String getEntity(boolean kotlin) {
        if (!this.disableEntity) {
            if (kotlin) {
                return StringUtils.isBlank(this.entityKt) ? "/templates/entity.kt" : this.entityKt;
            } else {
                return StringUtils.isBlank(this.entity) ? "/templates/vm/java/domain.java" : this.entity;
            }
        } else {
            return null;
        }
    }

    public EcareTemplateConfig disable(TemplateType... templateTypes) {
        if (templateTypes != null && templateTypes.length > 0) {
            TemplateType[] var2 = templateTypes;
            int var3 = templateTypes.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                TemplateType templateType = var2[var4];
                switch (templateType) {
                    case ENTITY:
                        this.entity = null;
                        this.entityKt = null;
                        this.disableEntity = true;
                        break;
                    case CONTROLLER:
                        this.controller = null;
                        break;
                    case MAPPER:
                        this.mapper = null;
                        break;
                    case XML:
                        this.xml = null;
                        break;
                    case SERVICE:
                        this.service = null;
                        break;
                    case SERVICE_IMPL:
                        this.serviceImpl = null;
                }
            }
        }

        return this;
    }

    public EcareTemplateConfig disable() {
        return this.disable(TemplateType.values());
    }

    public String getService() {
        return this.service;
    }

    public String getServiceImpl() {
        return this.serviceImpl;
    }

    public String getMapper() {
        return this.mapper;
    }

    public String getXml() {
        return this.xml;
    }

    public String getController() {
        return this.controller;
    }

    public static class Builder implements IConfigBuilder<EcareTemplateConfig> {
        private final EcareTemplateConfig templateConfig = new EcareTemplateConfig();

        public Builder() {
        }

        public EcareTemplateConfig.Builder disable() {
            this.templateConfig.disable();
            return this;
        }

        public EcareTemplateConfig.Builder disable(TemplateType... templateTypes) {
            this.templateConfig.disable(templateTypes);
            return this;
        }

        public EcareTemplateConfig.Builder entity(@NotNull String entityTemplate) {
            this.templateConfig.disableEntity = false;
            this.templateConfig.entity = entityTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder entityKt(@NotNull String entityKtTemplate) {
            this.templateConfig.disableEntity = false;
            this.templateConfig.entityKt = entityKtTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder service(@NotNull String serviceTemplate) {
            this.templateConfig.service = serviceTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder serviceImpl(@NotNull String serviceImplTemplate) {
            this.templateConfig.serviceImpl = serviceImplTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder mapper(@NotNull String mapperTemplate) {
            this.templateConfig.mapper = mapperTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder xml(@NotNull String xmlTemplate) {
            this.templateConfig.xml = xmlTemplate;
            return this;
        }

        public EcareTemplateConfig.Builder controller(@NotNull String controllerTemplate) {
            this.templateConfig.controller = controllerTemplate;
            return this;
        }

        public EcareTemplateConfig build() {
            return this.templateConfig;
        }
    }

}

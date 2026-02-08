package com.mcst.tag.config;

import com.mcst.tag.controller.TagController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Tag Client 控制器自动配置类
 *
 * @author Gemini
 */
@AutoConfiguration
public class TagClientAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TagController tagController() {
        return new TagController();
    }
}
package com.mcst.tag.config;

import com.mcst.tag.controller.TagBmsController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Tag BMS 控制器自动配置类
 *
 * @author Gemini
 */
@AutoConfiguration
public class TagBmsAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TagBmsController tagBmsController() {
        return new TagBmsController();
    }
}
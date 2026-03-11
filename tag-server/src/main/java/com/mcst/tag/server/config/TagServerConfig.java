package com.mcst.tag.server.config;

import com.mcst.tag.api.ITagApi;
import com.mcst.tag.server.impl.TagApiServerImpl;
import com.mcst.tag.server.service.ITagService;
import com.mcst.tag.server.service.impl.TagServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author liu yijun
 */
@AutoConfiguration
@EnableConfigurationProperties
public class TagServerConfig {

    @Bean
    @ConditionalOnMissingBean
    public ITagApi tagApi() {
        return new TagApiServerImpl();
    }


    @Bean
    @ConditionalOnMissingBean
    public ITagService tagService() {
        return new TagServiceImpl();
    }


}

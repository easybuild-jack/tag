package com.mcst.tag.server.config;

import com.mcst.tag.api.*;
import com.mcst.tag.server.service.*;
import com.mcst.tag.server.repository.*;
import com.mcst.tag.server.impl.*;
import com.mcst.tag.server.service.impl.*;
import com.mcst.tag.server.repository.impl.*;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
/**
*
* @author liu yijun
*/
@Configuration
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

    @Bean
    @ConditionalOnMissingBean
    public ITagRepository tagRepository() {
        return new TagRepositoryImpl();
    }


}

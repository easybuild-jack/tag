package com.mcst.tag.remote.config;


import com.mcst.tag.api.*;
import com.mcst.tag.remote.impl.*;
import com.mcst.tag.remote.properties.TagApiProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
*
* @author liu yijun
*/
@Configuration
@EnableConfigurationProperties(TagApiProperties.class)
public class TagApiConfig {
    @Bean
    @ConditionalOnMissingBean
    public ITagApi tagApi() {
         return new TagApiScImpl();
    }


}

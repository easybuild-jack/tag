package com.mcst.tag.remote.config;


import com.mcst.tag.api.ITagApi;
import com.mcst.tag.remote.impl.TagApiScImpl;
import com.mcst.tag.remote.properties.TagApiProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author liu yijun
 */
@AutoConfiguration
@EnableConfigurationProperties(TagApiProperties.class)
public class TagApiConfig {
    @Bean
    @ConditionalOnMissingBean
    public ITagApi tagApi() {
        return new TagApiScImpl();
    }


}

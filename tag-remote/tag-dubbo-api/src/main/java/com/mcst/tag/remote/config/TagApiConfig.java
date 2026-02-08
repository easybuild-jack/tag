package com.mcst.tag.remote.config;


import com.mcst.tag.api.ITagApi;
import com.mcst.tag.remote.impl.TagApiDubboImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author liu yijun
 */
@AutoConfiguration
public class TagApiConfig {
    @Bean
    @ConditionalOnMissingBean
    public ITagApi tagApi() {
        return new TagApiDubboImpl();
    }


}

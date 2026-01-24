package com.mcst.tag.remote.config;


import com.mcst.tag.api.*;
import com.mcst.tag.remote.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

/**
*
* @author liu yijun
*/
@Configuration
public class TagApiConfig {
    @Bean
    @ConditionalOnMissingBean
    public ITagApi tagApi() {
         return new TagApiDubboImpl();
    }


}

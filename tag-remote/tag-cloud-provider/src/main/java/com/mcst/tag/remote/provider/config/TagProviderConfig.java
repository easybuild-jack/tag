package com.mcst.tag.remote.provider.config;

import com.mcst.tag.remote.*;
import com.mcst.tag.remote.provider.impl.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
*
* @author liu yijun
*/
@Configuration
public class TagProviderConfig {

    @Bean
    @ConditionalOnMissingBean
    public ITagRemote tagRemote() {
         return new TagRemoteImpl();
    }


}

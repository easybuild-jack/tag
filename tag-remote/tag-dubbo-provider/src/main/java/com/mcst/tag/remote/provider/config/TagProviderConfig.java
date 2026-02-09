package com.mcst.tag.remote.provider.config;

import com.mcst.tag.remote.ITagRemote;
import com.mcst.tag.remote.provider.impl.TagRemoteImpl;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author liu yijun
 */
@AutoConfiguration
public class TagProviderConfig {

    @Bean
    @ConditionalOnMissingBean
    public ServiceConfig<ITagRemote> tagRemoteServiceConfig(TagRemoteImpl tagRemoteImpl) {
        ServiceConfig<ITagRemote> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(ITagRemote.class);
        serviceConfig.setRef(tagRemoteImpl);
        serviceConfig.export();
        return serviceConfig;
    }
}

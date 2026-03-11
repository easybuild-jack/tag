package com.mcst.tag.orm.plus.config;

import com.mcst.tag.orm.plus.repository.impl.TagRepositoryImpl;
import com.mcst.tag.orm.repository.ITagRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties
public class TagOrmPlusConfig {

    @Bean
    @ConditionalOnMissingBean
    public ITagRepository tagRepository() {
        return new TagRepositoryImpl();
    }

}

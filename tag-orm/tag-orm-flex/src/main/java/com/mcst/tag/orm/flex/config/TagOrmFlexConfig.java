package com.mcst.tag.orm.flex.config;

import com.mcst.tag.orm.flex.repository.impl.TagRepositoryImpl;
import com.mcst.tag.orm.repository.ITagRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Tag ORM MyBatis-Flex 自动配置
 *
 * @author liu yijun
 */
@AutoConfiguration
@EnableConfigurationProperties
public class TagOrmFlexConfig {

    @Bean
    @ConditionalOnMissingBean
    public ITagRepository tagRepository() {
        return new TagRepositoryImpl();
    }

}

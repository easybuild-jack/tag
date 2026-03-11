package com.mcst.tag.orm.hibernate.config;

import com.mcst.tag.orm.hibernate.repository.impl.TagRepositoryImpl;
import com.mcst.tag.orm.repository.ITagRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Tag ORM Hibernate 自动配置
 *
 * @author liu yijun
 */
@AutoConfiguration
@EnableConfigurationProperties
public class TagOrmHibernateConfig {

    @Bean
    @ConditionalOnMissingBean
    public ITagRepository tagRepository() {
        return new TagRepositoryImpl();
    }

}

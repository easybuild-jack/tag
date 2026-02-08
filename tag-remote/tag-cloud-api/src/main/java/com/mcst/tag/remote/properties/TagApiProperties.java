package com.mcst.tag.remote.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author liu yijun
 * @since 2026-01-23
 */
@ConfigurationProperties(prefix = "easyfk.config.remote.tag")
@Data
public class TagApiProperties {
    /**
     * Tag模块的ServiceId配置
     */
    private String serviceId;
    /**
     * path 前缀地址，如：/remote 或/api 等，默认/remote
     */
    private String basePath;
}

package com.yui.tools.anyjob.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "yui.any-job.cache")
public class CacheConfig {

    private int maxCache = 1024;

    private String url;
}

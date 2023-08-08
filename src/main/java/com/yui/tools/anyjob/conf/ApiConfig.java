package com.yui.tools.anyjob.conf;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "yui.any-job.wx.api")
public class ApiConfig {

    /**
     * https://api.weixin.qq.com
     */
    private String domain = "https://api.weixin.qq.com/";


    private List<ApiInfo> apiInfos = Arrays.asList(ApiInfo.builder().httpMethod(HttpMethod.GET).uri("cgi-bin/stable_token").build());


    @Builder
    @Data
    public static class ApiInfo {

        private HttpMethod httpMethod;

        private String uri;

    }
}

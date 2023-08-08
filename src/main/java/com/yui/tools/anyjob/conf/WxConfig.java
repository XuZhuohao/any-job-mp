package com.yui.tools.anyjob.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "yui.any-job.wx.info")
public class WxConfig {

    /**
     * 开发者ID是公众号开发识别码，配合开发者密码可调用公众号的接口能力。
     */
    private String appid;

    /**
     * 开发者密码是校验公众号开发者身份的密码，具有极高的安全性。切记勿把密码直接交给第三方开发者或直接存储在代码中。
     * 如需第三方代开发公众号，请使用授权方式接入。
     * 也可使用微信云托管免服务器免运维，支持免鉴权调用公众号接口能力，无需启用开发者密码及配置IP白名单，前往使用。
     */
    private String appSecret;

    /**
     * 服务器验证 token
     */
    private String token;


}

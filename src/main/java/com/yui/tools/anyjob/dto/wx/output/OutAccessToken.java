package com.yui.tools.anyjob.dto.wx.output;

import com.alibaba.fastjson2.annotation.JSONField;
import com.yui.tools.anyjob.conf.WxConfig;
import lombok.Data;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Data
public class OutAccessToken {

    @JSONField(name = "grant_type")
    private String grantType;

    private String appid;

    private String secret;

    public OutAccessToken(WxConfig wxConfig) {
        this.appid = wxConfig.getAppid();
        this.secret = wxConfig.getAppSecret();
        this.grantType = "client_credential";
    }

}

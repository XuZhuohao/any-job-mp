package com.yui.tools.anyjob.dto.wx.input;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Data
public class InAccessToken {
    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private String expiresIn;

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;
}

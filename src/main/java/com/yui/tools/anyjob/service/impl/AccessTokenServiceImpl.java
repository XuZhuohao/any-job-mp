package com.yui.tools.anyjob.service.impl;

import com.yui.tools.anyjob.conf.WxConfig;
import com.yui.tools.anyjob.dto.wx.input.InAccessToken;
import com.yui.tools.anyjob.dto.wx.output.OutAccessToken;
import com.yui.tools.anyjob.service.AccessTokenService;
import com.yui.tools.anyjob.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Slf4j
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private Map<String, String> CACHE_MAP = new HashMap<>(4);

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private WxService wxService;

    @Override
    public String getToken(String grantType, boolean refresh) {
        if (!refresh && CACHE_MAP.containsKey(grantType)) {
            return CACHE_MAP.get(grantType);
        }
        //
        this.accessToken(grantType);
        return CACHE_MAP.get(grantType);
    }

    public void accessToken(String grantType) {
        OutAccessToken outAccessToken = new OutAccessToken(wxConfig);
        outAccessToken.setGrantType(grantType);
        InAccessToken inAccessToken = wxService.requestByJson(outAccessToken, InAccessToken.class);
        String accessToken = inAccessToken.getAccessToken();
        if (accessToken == null || accessToken.isBlank()) {
            //error
        }
        CACHE_MAP.put(grantType, accessToken);

    }

    public void autoRefresh() {
        CACHE_MAP.keySet().forEach(this::accessToken);
    }


}

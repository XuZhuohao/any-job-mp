package com.yui.tools.anyjob.service;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
public interface AccessTokenService {

    /**
     * 获取token
     *
     * @return 结国
     */
    String getToken(String grantType, boolean refresh);
}

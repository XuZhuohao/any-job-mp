package com.yui.tools.anyjob.dto.wx.input;

import cn.hutool.crypto.SecureUtil;
import com.yui.tools.anyjob.conf.WxConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InAccessOverview {
    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * nonce
     */
    private String nonce;

    /**
     * echostr
     */
    private String echostr;

    public boolean valid(WxConfig wxConfig) {
        List<String> validData = Arrays.asList(wxConfig.getToken(), this.getTimestamp(), this.getNonce());
        String validStr = validData.stream().sorted().collect(Collectors.joining());
        String signatureLocal = SecureUtil.sha1(validStr);
        System.out.println("=============local signature=========:" + signatureLocal);
        System.out.println("=============   signature   =========:" + signature);
        return signatureLocal.equals(signature);
    }
}

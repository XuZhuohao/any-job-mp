package com.yui.tools.anyjob.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yui.tools.anyjob.conf.ApiConfig;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.dto.wx.output.OutRMNormalText;
import com.yui.tools.anyjob.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Slf4j
@Service
public class WxServiceImpl implements WxService {

    @Autowired
    private ApiConfig apiConfig;

    @Override
    public <T> T requestByJson(Object json, Class<T> outputType) {
        String param = JSONObject.toJSONString(json);
        log.info("入参信息: {}", param);
        // 这里需要添加 accessToken 与 接口获取
        ApiConfig.ApiInfo apiInfo = apiConfig.getApiInfos().get(0);
        String post = HttpUtil.post(apiConfig.getDomain() + apiInfo.getUri(), param);
        log.info("结果信息: {}", post);
        return JSON.parseObject(post, outputType);
    }

    @Override
    public InReceivingMessage replyMessage(Object result, InReceivingMessage inReceivingMessage) {
        // 目前只做文本恢复
        OutRMNormalText normalText = new OutRMNormalText();
        BeanUtils.copyProperties(inReceivingMessage, normalText);
        normalText.setFromUserName(inReceivingMessage.getToUserName());
        normalText.setToUserName(inReceivingMessage.getFromUserName());
        normalText.setMsgType("text");
        if (result instanceof String) {
            normalText.setContent((String)result);
        } else {
            JSONObject from = JSONObject.from(result);
            if (from == null) {
                normalText.setContent(result.toString());
                return normalText;
            }
            StringBuilder sb = new StringBuilder();
            from.forEach((key, value) -> sb.append(key).append(":").append(value).append("\r\n"));
            normalText.setContent(sb.toString());
        }

        return normalText;
    }


}

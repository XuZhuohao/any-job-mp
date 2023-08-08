package com.yui.tools.anyjob.service;

import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
public interface WxService {

    <T>T requestByJson(Object json, Class<T> outputType);

    InReceivingMessage replyMessage(Object result, InReceivingMessage inReceivingMessage);
}

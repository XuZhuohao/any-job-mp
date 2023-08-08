package com.yui.tools.anyjob.service;

import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
public interface AnyJobService<T> {

    /**
     * 配置 key
     * @return 固定
     */
    String getKey();

    /**
     * 处理进程
     * @param inReceivingMessage 入参
     * @return 结果
     */
    T process(InReceivingMessage inReceivingMessage);


}

package com.yui.tools.anyjob.service;

import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;

import java.util.function.Function;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
public interface AsyncAnyJobService {

    /**
     * 处理进程
     * @param inReceivingMessage 入参
     * @return 结果
     */
    <T> long process(InReceivingMessage inReceivingMessage, Function<InReceivingMessage, T> anyJobService);

    Object getResult(long key);

}

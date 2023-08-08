package com.yui.tools.anyjob.service;

import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;

import java.util.function.Function;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
public interface JobRunService {

    Function<InReceivingMessage, Object> select(InRMNormalText text);
}

package com.yui.tools.anyjob.dto.wx.job;

import lombok.Data;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Data
public class CallMessageDto {

    private String method;

    private String url;

    private String body;
}

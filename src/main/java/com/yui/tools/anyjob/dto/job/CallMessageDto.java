package com.yui.tools.anyjob.dto.job;

import com.yui.tools.anyjob.common.annotation.Intro;
import lombok.Data;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Data
@Intro("调用信息")
public class CallMessageDto {

    @Intro("Get 代表 get 请求，否则代表 post 请求")
    private String method;

    @Intro("目标地址，如果 url 参数，需要在url后面拼接")
    private String url;

    @Intro("消息体，json 格式，post 请求使用")
    private String body;
}

package com.yui.tools.anyjob.dto.job;

import com.yui.tools.anyjob.common.annotation.Intro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Intro("回调结果")
public class CallResultDto {
    @Intro("调用请求信息")
    private CallMessageDto request;

    @Intro("调用结果")
    private String result;
}

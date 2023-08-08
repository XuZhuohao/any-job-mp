package com.yui.tools.anyjob.dto.wx.job;

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
public class CallResultDto {

    private CallMessageDto request;

    private String result;
}

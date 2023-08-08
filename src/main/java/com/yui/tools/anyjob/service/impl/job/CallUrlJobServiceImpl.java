package com.yui.tools.anyjob.service.impl.job;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.yui.tools.anyjob.common.annotation.HelpTools;
import com.yui.tools.anyjob.common.annotation.Intro;
import com.yui.tools.anyjob.dto.job.CallMessageDto;
import com.yui.tools.anyjob.dto.job.CallResultDto;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */

@Intro("通过参数对url进行调用")
@Primary
@Service("CallUrlJob")
public class CallUrlJobServiceImpl implements AnyJobService<CallResultDto> {
    @Override
    public String getKey() {
        return "CallUrl";
    }

    @Override
    public CallResultDto process(InReceivingMessage inReceivingMessage) {
        String content = ((InRMNormalText) inReceivingMessage).getContent();
        CallMessageDto callMessageDto = JSON.parseObject(content, CallMessageDto.class);
        String result;
        if (callMessageDto.getMethod().equals("Get")) {
            result = HttpUtil.get(callMessageDto.getUrl());
        } else {
            result = HttpUtil.post(callMessageDto.getUrl(), callMessageDto.getBody());
        }
        return CallResultDto.builder().result(result).request(callMessageDto).build();
    }

    @Override
    public String help(InReceivingMessage help) {
        return "====入参=====\n" +
                HelpTools.help(CallMessageDto.class) +
                "\n====结果=====\n" +
                HelpTools.help(CallResultDto.class);
    }

}

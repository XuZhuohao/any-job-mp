package com.yui.tools.anyjob.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import com.yui.tools.anyjob.service.JobRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
@Slf4j
@Service
public class JobRunServiceImpl implements JobRunService {

    static private Map<String, AnyJobService<?>> SERVICES = new HashMap<>(16);

    @Autowired
    public void setServices(List<AnyJobService<?>> anyJobService) {
        log.info(JSON.toJSONString(anyJobService));
        anyJobService.forEach(temp -> SERVICES.put(temp.getKey(), temp));
        log.info("service:{}", JSON.toJSONString(SERVICES));
    }

    @Override
    public Function<InReceivingMessage, Object> select(InRMNormalText text) {
        String content = text.getContent();
        int first = content.indexOf(":");
        String methodKey = content.substring(0, first);
        String param = content.substring(first + 1);
        InRMNormalText runMessage = new InRMNormalText();
        BeanUtil.copyProperties(text, runMessage);
        runMessage.setContent(param);
        if (SERVICES.containsKey(methodKey)) {
            return SERVICES.get(methodKey)::process;
        }
        return (t) -> "没有对应的方法";
    }

}

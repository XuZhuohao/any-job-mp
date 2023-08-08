package com.yui.tools.anyjob.controller;

import com.alibaba.fastjson2.JSON;
import com.yui.tools.anyjob.conf.CacheConfig;
import com.yui.tools.anyjob.conf.WxConfig;
import com.yui.tools.anyjob.dto.Result;
import com.yui.tools.anyjob.dto.job.AsyncInfoDto;
import com.yui.tools.anyjob.dto.wx.input.InAccessOverview;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import com.yui.tools.anyjob.service.AsyncAnyJobService;
import com.yui.tools.anyjob.service.JobRunService;
import com.yui.tools.anyjob.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Slf4j
@RequestMapping("wx")
@RestController
public class WxMessageController {
    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private AnyJobService anyJobService;
    @Autowired
    private WxService wxService;
    @Autowired
    private AsyncAnyJobService asyncAnyJobService;
    @Autowired
    private CacheConfig cacheConfig;
    @Autowired
    private JobRunService jobRunService;

    @GetMapping
    public String valid(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam("echostr") String echostr
    ) {
        InAccessOverview overview = InAccessOverview.builder().signature(signature).timestamp(timestamp).nonce(nonce).echostr(echostr).build();
        if (overview.valid(wxConfig)) {
            return echostr;
        }
        return "错误";
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public InReceivingMessage receiving(@RequestBody InReceivingMessage inReceivingMessage) {
        log.info("message: {}", JSON.toJSONString(inReceivingMessage));
        //TODO:请求去重
        Function<InReceivingMessage, Object> func;
        if ("text".equals(inReceivingMessage.getMsgType())) {
            Result<Function<InReceivingMessage, Object>> selectResult = jobRunService.select((InRMNormalText) inReceivingMessage);
            if (!selectResult.isSuccess()) {
                return wxService.replyMessage(selectResult.getMessage(), inReceivingMessage);
            }
            func = selectResult.getData();
        } else {
            return wxService.replyMessage("感谢关注，自动应答只支持文字信息", inReceivingMessage);
        }
        AsyncInfoDto job = asyncAnyJobService.process(inReceivingMessage, func);
        try {
            Object o = job.getFuture().get(2, TimeUnit.SECONDS);
            return wxService.replyMessage(o, inReceivingMessage);
        } catch (Exception e) {
            log.error("异常：{}",e.getMessage(), e);
            return wxService.replyMessage(cacheConfig.getUrl() + job.getKey(), inReceivingMessage);
        }
    }
}

package com.yui.tools.anyjob.controller;

import com.alibaba.fastjson2.JSON;
import com.yui.tools.anyjob.conf.CacheConfig;
import com.yui.tools.anyjob.conf.WxConfig;
import com.yui.tools.anyjob.dto.wx.input.InAccessOverview;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import com.yui.tools.anyjob.service.AsyncAnyJobService;
import com.yui.tools.anyjob.service.JobRunService;
import com.yui.tools.anyjob.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        try {
            long key;
            if ("text".equals(inReceivingMessage.getMsgType())) {
                key = asyncAnyJobService.process(inReceivingMessage, jobRunService.select((InRMNormalText) inReceivingMessage));
            } else {
                return wxService.replyMessage("只支持文字信息", inReceivingMessage);
            }
            return wxService.replyMessage(cacheConfig.getUrl() + key, inReceivingMessage);
        } catch (Exception e) {
            log.error("exception", e);
            return null;
        }
    }
}

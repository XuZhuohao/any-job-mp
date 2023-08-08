package com.yui.tools.anyjob.dto.wx.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.yui.tools.anyjob.conf.MessageDeserializer;
import lombok.Data;
import lombok.ToString;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Data
@ToString
@JacksonXmlRootElement(localName ="xml")
@JsonDeserialize(using = MessageDeserializer.class)
public class InReceivingMessage {

    @JsonProperty("ToUserName")
    private String toUserName;

    @JsonProperty("FromUserName")
    private String fromUserName;

    @JsonProperty("CreateTime")
    private long createTime;

    @JsonProperty("MsgType")
    private String msgType;

    @JsonIgnore
    private Class<? extends  InReceivingMessage> realType;

}

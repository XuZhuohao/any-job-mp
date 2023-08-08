package com.yui.tools.anyjob.dto.wx.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.yui.tools.anyjob.conf.MessageDeserializer;
import lombok.*;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@JacksonXmlRootElement(localName ="xml")
public class InRMNormalText extends InReceivingMessage {
    @JsonProperty("Content")
    private String content;
    @JsonProperty("MsgId")
    private String msgId;
    @JsonProperty("MsgDataId")
    private String msgDataId;
    @JsonProperty("Idx")
    private String idx;

    public void nullOut() {
        this.msgId = null;
        this.msgDataId = null;
        this.idx = null;
    }
}

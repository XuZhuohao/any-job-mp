package com.yui.tools.anyjob.dto.wx.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
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
public class OutRMNormalText extends InReceivingMessage {
    @JsonProperty("Content")
    private String content;
}

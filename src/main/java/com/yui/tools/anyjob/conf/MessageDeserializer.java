package com.yui.tools.anyjob.conf;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;

import java.io.IOException;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
public class MessageDeserializer extends JsonDeserializer<InReceivingMessage> {
    @Override
    public InReceivingMessage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        TreeNode msgTypeNode = treeNode.get("MsgType");
        String msgType = jsonParser.getCodec().treeToValue(msgTypeNode, String.class);

        JSONObject rootMessage = jsonParser.getCodec().treeToValue(treeNode, JSONObject.class);
        if ("text".equals(msgType)) {
            InRMNormalText javaObject = rootMessage.toJavaObject(InRMNormalText.class);
            javaObject.setRealType(InRMNormalText.class);
            return javaObject;
        }
        return rootMessage.toJavaObject(InReceivingMessage.class);
    }
}

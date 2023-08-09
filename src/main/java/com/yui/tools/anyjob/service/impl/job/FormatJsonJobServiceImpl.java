package com.yui.tools.anyjob.service.impl.job;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.yui.tools.anyjob.common.annotation.Intro;
import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Slf4j
@Intro("格式化json")
@Service("formatJsonJob")
public class FormatJsonJobServiceImpl implements AnyJobService<String> {


    @Override
    public String getKey() {
        return "formatJson";
    }
    private static final Pattern PATTERN_OF_EXCEPTION = Pattern.compile(": (\\d+)+");
    @Override
    public String process(InReceivingMessage inReceivingMessage) {
        String content = ((InRMNormalText) inReceivingMessage).getContent();
        try {
            JSONObject jsonObject = JSON.parseObject(content);
            return JSON.toJSONString(jsonObject, JSONWriter.Feature.PrettyFormat);
        } catch (JSONException e) {
            return jsonExceptionTip(content, e.getMessage());
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String help(InReceivingMessage help) {
        return "将入参格式化，作为出参输出";
    }

    public static String jsonExceptionTip(String content, String message) {
        System.out.println(message);
        Matcher matcher = PATTERN_OF_EXCEPTION.matcher(message);
        if (!matcher.find()) {
            return message;
        }
        int group = Integer.parseInt(matcher.group(1));
        int start = 0;
        int end = content.length();
        if (end > group + 5) {
            end = group + 5;
        }
        if (group - 5 > 0) {
            start = group -5;
        }
        return message + "\n" + content.substring(start, end) + "\n" ;
    }

//    public static void main(String[] args) {
//        FormatJsonJobServiceImpl service = new FormatJsonJobServiceImpl();
//        InRMNormalText inRMNormalText = new InRMNormalText();
//        inRMNormalText.setContent("{\"test\":\"1\", \"isObject\":[{\"test\": 1, \"test2\" ，: 2}]}");
//        String process = service.process(inRMNormalText);
//        System.out.println(process);
//    }


}

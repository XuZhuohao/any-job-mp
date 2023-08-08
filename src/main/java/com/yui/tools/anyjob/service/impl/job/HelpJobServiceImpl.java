package com.yui.tools.anyjob.service.impl.job;

import com.yui.tools.anyjob.common.annotation.Intro;
import com.yui.tools.anyjob.dto.wx.input.InReceivingMessage;
import com.yui.tools.anyjob.service.AnyJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-07
 */
@Slf4j
@Intro("帮助指令")
@Service("HelpJob")
public class HelpJobServiceImpl implements AnyJobService<String> {

    @Autowired
    private ApplicationContext applicationContext;
    public static String helpStr = null;

    @Override
    public String getKey() {
        return "help";
    }

    @Override
    public String process(InReceivingMessage inReceivingMessage) {
        return initHelp();
    }

    @Override
    public String help(InReceivingMessage help) {
        return initHelp();
    }

    public String initHelp() {
        if (helpStr != null) {
            return helpStr;
        }
        Map<String, AnyJobService> beansOfType = applicationContext.getBeansOfType(AnyJobService.class);
        StringBuilder sb = new StringBuilder("显示所有命令：");
        beansOfType.values().stream()
                .filter(Objects::nonNull)
                .filter(temp -> temp.getClass().getAnnotation(Intro.class) != null)
                .forEach(temp -> sb.append("\n").append("\t\t\t- ").append(temp.getKey())
                        .append(":")
                        .append(temp.getClass().getAnnotation(Intro.class).value()));
        sb.append("\n通过输入 [命令]:help 查看对应命令的说明 \n");
        sb.append("所有命令如果执行时间过长，会放回一个任务结果查看地址，可以通过范围该地址获得结果");
        helpStr = sb.toString();
        return helpStr;
    }

}

package com.yui.tools.anyjob.common.annotation;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.databind.util.LRUMap;

import java.lang.reflect.Field;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
public class HelpTools {

    private final static LRUMap<Class<?>, String> CACHE_LRU = new LRUMap<>(16, 1024);

    /**
     * 获取参数说明
     *
     * @param clazz class
     * @return 结果
     */
    public static String help(Class<?> clazz) {
        String s = CACHE_LRU.get(clazz);
        if (s != null) {
            return s;
        }
        Intro typeIntro = clazz.getAnnotation(Intro.class);
        StringBuilder sb = new StringBuilder(typeIntro.value());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Intro annotation = field.getAnnotation(Intro.class);
            sb.append("\n\t\t\t- ").append(field.getName()).append(": ").append(annotation.value());
        }
        CACHE_LRU.put(clazz, sb.toString());
        return CACHE_LRU.get(clazz);
    }

    /**
     * 获取参数模板
     *
     * @param clazz class
     * @return 结果
     */
    public static String getTemplate(Class<?> clazz) {
        try {
            Object o = clazz.getConstructor().newInstance();
            return JSON.toJSONString(o, JSONWriter.Feature.WriteNullStringAsEmpty,
                    JSONWriter.Feature.WriteMapNullValue,
                    JSONWriter.Feature.WriteNullListAsEmpty,
                    // 格式化输出
                    JSONWriter.Feature.PrettyFormat);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 构建 help 文本
     *
     * @param paramClazz  入参类型
     * @param resultClazz 结果类型
     * @return 完整
     */
    public static String buildHelp(Class<?> paramClazz, Class<?> resultClazz) {
        StringBuilder sb = new StringBuilder();
        if (paramClazz != null) {
            sb.append("====入参=====\n").append(HelpTools.help(paramClazz));
        }
        if (resultClazz != null) {
            sb.append("\n====结果=====\n").append(HelpTools.help(resultClazz));
        }

        if (paramClazz != null) {
            sb.append("\n===入参模板====\n").append(HelpTools.getTemplate(paramClazz));
        }
        return sb.toString();
    }

}

package com.yui.tools.anyjob.common.annotation;

import com.fasterxml.jackson.databind.util.LRUMap;
import com.yui.tools.anyjob.dto.job.AsyncInfoDto;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

/**
 * @author Yui_HTT -- haogg
 * @version 1.0.0
 * @date 2023-08-08
 */
public class HelpTools {

    private final static LRUMap<Class<?>, String> CACHE_LRU = new LRUMap<>(16, 1024);
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

    public static void main(String[] args) {
        System.out.println(HelpTools.help(AsyncInfoDto.class));
    }
}

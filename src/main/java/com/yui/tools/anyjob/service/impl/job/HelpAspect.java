//package com.yui.tools.anyjob.service.impl.job;
//
//import com.yui.tools.anyjob.dto.wx.input.InRMNormalText;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @author Yui_HTT -- haogg
// * @version 1.0.0
// * @date 2023-08-08
// */
//@Aspect
//@Component
//public class HelpAspect {
//    @Pointcut("execution (* com.yui.tools.anyjob.dto.job.*.process(..))")
//    public void point() {
//
//    }
//
//    @Around("point()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        Object[] args = pjp.getArgs();
//        // 强制转为 InRMNormalText
//        String content = ((InRMNormalText) args[0]).getContent();
//        if (content == null) {
//            return pjp.proceed();
//        }
//        String trim = content.trim();
//        if (!"help".equals(trim)) {
//            return pjp.proceed();
//        }
//        Object target = pjp.getTarget();
//        pjp.getClass().getMethod("help").invoke(target)
//        return pjp.proceed();
//    }
//}

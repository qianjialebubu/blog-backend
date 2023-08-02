package com.example.blog2.common.aop;

import com.alibaba.fastjson.JSON;
import com.example.blog2.util.HttpContextUtils;
import com.example.blog2.util.IpMacUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @program: myblog-backendv1.5
 * @description: 注解
 * @author: qjl
 * @create: 2023-07-31 17:33
 **/
@Component
@Aspect // 切面，定义通知和切点的关系
@Slf4j
public class LogAspect {


    @Pointcut("@annotation(com.example.blog2.common.aop.LogAnnotation)")
    public void pt(){
    }

    // 环绕通知
    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        // 记录日志
        recordLog(joinPoint,time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("====================log start==================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className+"."+methodName+"()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);
        // 获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpMacUtil.getIpAddress(request));
        log.info("excute time : {} ms",time);
        log.info("====================log end==================");

    }


}

package com.example.comumnity.domain.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class TimerAop {


    @Pointcut("execution(* com.example.comumnity.domain.board.web.controller..*.*(..))")
    private void cut(){

    }

    @Pointcut("@annotation(com.example.comumnity.global.annotation.Timer)")
    private void enableTimer(){

    }


    @Around("cut() && enableTimer()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        Double totalTime = stopWatch.getTotalTimeSeconds();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        log.info("실행 메서드 : {}, 실행시간 = {}", methodName, totalTime);
        return result;

    }

}

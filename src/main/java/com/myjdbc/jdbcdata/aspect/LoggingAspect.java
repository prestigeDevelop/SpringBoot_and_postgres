package com.myjdbc.jdbcdata.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.myjdbc.jdbcdata.service.*.*(..))")  // Pointcut
    public void logMethodCall(JoinPoint jp) {
        System.out.println("Method called: " + jp.getSignature());
    }


    //@Around("execution(* com.myjdbc.jdbcdata.pg.service.*.saveUser(..))")
    @Around("execution(* com.myjdbc.jdbcdata.pg.service.*.*(..))")
    //@Around("execution(* com.myjdbc.jdbcdata.pg.service.UserService.*(..))")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();
        log.info("Execution time: {}ms for method: {}", stopWatch.getTotalTimeMillis(), pjp.getSignature().getName());
        return result;
    }

    @Before("execution(* com.myjdbc.jdbcdata.pg.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("🟢 BEFORE - Aspect working for: {}", joinPoint.getSignature().getName());
    }
}

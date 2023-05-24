package com.example.weatherbuddy.aspect;

import com.example.weatherbuddy.logger.ServiceLogger;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ServiceLogger serviceLogger;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(com.example.weatherbuddy.controller..*)")
    public void logController(){}

    @Pointcut("within(com.example.weatherbuddy.service..*)" +
            " || within(com.example.weatherbuddy.controller..*)")
    public void applicationPackagePointcut() {
    }

    @Around("logController()")
    public Object logApi(ProceedingJoinPoint pjp) throws Throwable {
        return serviceLogger.log(pjp);
    }


    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }
}

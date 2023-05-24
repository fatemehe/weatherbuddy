package com.example.weatherbuddy.logger;

import com.example.weatherbuddy.logger.dto.LogDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ServiceLogger {

    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        LogDto logDto = createLogDto(pjp);
        logRequest(logDto);
        Object response;
        try {
            response = logResponse(logDto);
        } catch (Throwable e) {
            logException(logDto, e);
            throw e;
        }
        return response;
    }

    protected abstract void logRequest(LogDto logDto);

    protected abstract void logResponse(LogDto logDto, Object result);

    protected abstract void logException(LogDto logDto, Throwable e);

    protected abstract void logRawData(LogDto logDto, String message);

    private LogDto createLogDto(ProceedingJoinPoint pjp) {
        Logger logger = LoggerFactory.getLogger(pjp.getSourceLocation().getWithinType());
        LogDto logDto = new LogDto();
        logDto.setLogger(logger);
        logDto.setPjp(pjp);
        logDto.setPosition(pjp.getSignature().getName());
        return logDto;
    }

    private Object logResponse(LogDto logDto) throws Throwable {
        if (((MethodSignature) logDto.getPjp().getSignature()).getReturnType().equals(Flux.class)) {
            return logFluxResponse(logDto);
        }
        if (((MethodSignature) logDto.getPjp().getSignature()).getReturnType().equals(Mono.class)) {
            return logMonoResponse(logDto);
        }
        return logNormalResponse(logDto);
    }

    private Object logFluxResponse(LogDto logDto) throws Throwable {
        return ((Flux<?>) logDto.getPjp().proceed())
                .doOnNext(o -> logResponse(logDto, o))
                .doOnError(e -> logException(logDto, e))
                .doOnComplete(() -> logRawData(logDto, "request is completed"))
                .doOnCancel(() -> logRawData(logDto, "request is canceled"))
                .doFinally(f -> logDto.getLogger().info("Done!"));
    }

    private Object logMonoResponse(LogDto logDto) throws Throwable {
        return ((Mono<?>) logDto.getPjp().proceed())
                .doOnNext(r -> logResponse(logDto, r))
                .doOnError(e -> logException(logDto, e))
                .doOnCancel(() -> logRawData(logDto, "request is canceled"))
                .doFinally(f -> logDto.getLogger().info("Done!"));
    }

    private Object logNormalResponse(LogDto logDto) throws Throwable {
        Object result = logDto.getPjp().proceed();
        logResponse(logDto, result);
        return result;

    }

}

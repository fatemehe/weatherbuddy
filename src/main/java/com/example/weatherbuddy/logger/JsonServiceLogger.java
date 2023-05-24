package com.example.weatherbuddy.logger;

import com.example.weatherbuddy.logger.dto.LogDto;
import com.example.weatherbuddy.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JsonServiceLogger extends ServiceLogger {

    private final JsonUtil jsonUtil;

    @Override
    protected void logRequest(LogDto logDto) {
        String[] parameterNames = ((MethodSignature) logDto.getPjp().getSignature()).getParameterNames();
        String requestLog = createJson("request", logDto.getPosition(), logDto.getPjp().getArgs(), parameterNames);
        Logger logger = logDto.getLogger();
        logger.info(requestLog);
    }

    @Override
    protected void logResponse(LogDto logDto, Object result) {
        String responseLog = createJson("response", logDto.getPosition(), new Object[]{result}, null);
        logDto.getLogger().info(responseLog);
    }


    @Override
    protected void logException(LogDto logDto, Throwable ex) {
        Map<String, Object> exception = new LinkedHashMap<>();
        exception.put("name", ex.getClass().getSimpleName());
        exception.put("message", ex.getMessage());
        exception.put("localizedMessage", ex.getLocalizedMessage());
        exception.put("stackTrace", getStackTrace(ex));
        String exceptionLog = createJson("exception", logDto.getPosition(), new Object[]{exception}, null);
        logDto.getLogger().info(exceptionLog);
    }

    @Override
    protected void logRawData(LogDto logDto, String message) {
        Map<String, String> logMap = new LinkedHashMap<>();
        logMap.put("service", logDto.getPosition());
        logMap.put("message", message);
        logDto.getLogger().info(jsonUtil.objectToJson(logMap));

    }

    private List<String> getStackTrace(final Throwable throwable) {
        List<String> stackTrace = new ArrayList<>();
        for (StackTraceElement element : throwable.getStackTrace()) {
            stackTrace.add(element.toString());
            if (stackTrace.size() > 10) {
                return stackTrace;
            }
        }
        return stackTrace;
    }

    private String createJson(String key, String serviceName, Object[] methodArgs, Object[] parameterNames) {
        Map<String, Object> object = new LinkedHashMap<>(3);
        object.put("service", serviceName);
        if (methodArgs != null) {
            Map<String, Object> objectsMap = new LinkedHashMap<>(methodArgs.length);
            for (int i = 0; i < methodArgs.length; i++) {
                Object obj = methodArgs[i];
                if (obj != null) {
                    if (parameterNames != null && parameterNames.length > 1 && parameterNames[i] != null) {
                        objectsMap.put((String) parameterNames[i], obj);
                    } else {
                        objectsMap.put(obj.getClass().getSimpleName(), obj);
                    }
                }
                object.put(key, objectsMap);
            }
        }
        return jsonUtil.objectToJson(object);
    }


}

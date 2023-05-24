package com.example.weatherbuddy.logger.dto;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;


@Data
public class LogDto {
    private ProceedingJoinPoint pjp;
    private String position;
    private Logger logger;
}

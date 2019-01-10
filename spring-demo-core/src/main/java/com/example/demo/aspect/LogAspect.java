package com.example.demo.aspect;

import com.example.demo.aspect.annotation.LogAdvice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  @Pointcut("@annotation(com.example.demo.aspect.annotation.LogAdvice)")
  private void pointCut() {}

  @Around("pointCut()")
  public Object log(ProceedingJoinPoint pjp) throws Throwable {
    Object returnVal = null;
    Signature signature = pjp.getSignature();
    if (signature instanceof MethodSignature) {
      MethodSignature methodSignature = (MethodSignature) signature;
      Method method = methodSignature.getMethod();
      LogAdvice logAdvice = method.getAnnotation(LogAdvice.class);
      logger.info("log name is {}", logAdvice.name());
    }
    Object[] objects = pjp.getArgs();
    try {
      logger.info("before");

      for (Object object : objects) {
        logger.info("argu:{}", object);
      }
      returnVal = pjp.proceed();
      logger.info("after");

    } catch (Exception e) {
      logger.info("error");
    }
    return returnVal;
  }
}

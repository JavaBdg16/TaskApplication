package pl.sda.taskapplication.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TaskServiceLoggingAspect {

    @Around("execution(* pl.sda.taskapplication.service.TaskService..*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        Logger logger = LoggerFactory.getLogger("doLogging");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        logger.info(":: start " + methodName + " ::");

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long time = end - start;

        logger.info("Method " + methodName + " execution lasted: " + time + " ms");
        logger.info("Method " + methodName + " execution ended at: " + new Date());

        logger.warn(proceed.toString());
        logger.info(":: end ::");

        return proceed;
    }
}

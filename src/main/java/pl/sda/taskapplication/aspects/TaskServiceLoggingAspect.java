package pl.sda.taskapplication.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TaskServiceLoggingAspect {

    @Around("execution(* pl.sda.taskapplication.service.TaskService..*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        System.out.println(":: start " + methodName + " ::");

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long time = end - start;

        System.out.println("Method " + methodName + " execution lasted: " + time + " ms");
        System.out.println("Method " + methodName + " execution ended at: " + new Date());

        System.out.println(proceed);
        System.out.println(":: end ::");

        return proceed;
    }
}

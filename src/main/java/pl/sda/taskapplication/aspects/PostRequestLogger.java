package pl.sda.taskapplication.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Component
@Aspect
public class PostRequestLogger {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postAction() {
    }

    @Before("postAction()")
    public void logAction(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();
        String url = getRequestUrl(joinPoint, clazz);
        String payload = getPayload(joinPoint);

        Logger logger = LoggerFactory.getLogger("PostMappingLogger");
        logger.info("POST " + url + " Payload " + payload);
    }

    private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PostMapping methodPostMapping = method.getAnnotation(PostMapping.class);
        RequestMapping methodRequestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);

        String baseUrl = methodRequestMapping.path().length == 0
                ? ""
                : methodRequestMapping.path()[0];

        String endpoint = methodPostMapping.path().length == 0
                ? ""
                : methodPostMapping.path()[0];

        return baseUrl + endpoint;
    }

    private String getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            stringBuilder.append(parameterName);
            stringBuilder.append(": ");
            stringBuilder.append(joinPoint.getArgs()[i].toString());
            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }
}

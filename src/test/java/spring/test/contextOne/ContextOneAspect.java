package spring.test.contextOne;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContextOneAspect {

    @Around("execution(* spring.test.contextOne..foo())")
    public Object contextTwoAspect(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("ContextOneAspect + Around foo");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }
}


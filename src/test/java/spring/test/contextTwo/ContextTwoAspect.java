package spring.test.contextTwo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class ContextTwoAspect {

    @Around("execution(* spring.test.contextTwo..foo())")
    public Object contextTwoAspect(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("ContextTwoAspect + Around foo");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }
}


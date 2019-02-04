package spring.test.contextTre;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class ContextTreAspect {

    @Around("execution(* spring.test.contextTre..foo())")
    public Object contextTwoAspect(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("ContextTreAspect + Around foo");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }
}


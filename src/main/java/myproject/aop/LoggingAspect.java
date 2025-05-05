package myproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(public * myproject..*.*(..)) && " +
            "(@target(org.springframework.stereotype.Component) || " +
            "@target(org.springframework.stereotype.Service) || " +
            "@target(org.springframework.stereotype.Repository))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.printf("[BEAN LOG] ==> %s.%s() with args: %s%n",
                className, methodName,
                java.util.Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            stopWatch.stop();

            System.out.printf("[BEAN LOG] <== %s.%s() [execution: %dms] | Result: %s%n",
                    className, methodName,
                    stopWatch.getTotalTimeMillis(),
                    result != null ? result.toString() : "void");

            return result;
        } catch (Exception e) {
            stopWatch.stop();
            System.out.printf("[BEAN ERROR] !!! %s.%s() [failed after %dms] | Exception: %s%n",
                    className, methodName,
                    stopWatch.getTotalTimeMillis(),
                    e.toString());
            throw e;
        }
    }
}
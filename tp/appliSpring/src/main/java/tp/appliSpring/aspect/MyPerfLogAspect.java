package tp.appliSpring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("perf")
public class MyPerfLogAspect {
	@Around("execution(* tp.appliSpring.core.service.*.*(..))")
	public Object doXxxLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		System.out.println(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}
}

package tp.appliSpring.exemple_advisor;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyLoggingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("** Method name : "	+ invocation.getMethod().getName());
		System.out.println("** Method arguments : "+ Arrays.toString(invocation.getArguments()));
		Object result = invocation.proceed();
		System.out.println("** Method result : "	+ result.toString());
		return result;
	}
     
}

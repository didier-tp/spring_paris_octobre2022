package tp.appliSpring.exemple_advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UppercaseInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = invocation.proceed();
		if(result instanceof String) {
			result=((String) result).toUpperCase();
		}
		return result;
	}

}

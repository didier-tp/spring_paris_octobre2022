package tp.appliSpring.exemple_advisor;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml ou java
@Configuration
public class MyOldAopConfig {
	
	@Bean
	Thing basicThing() {
		return new BasicThing("nb_continents" , "5");
	}
	
	
	@Bean
	Thing basicThingProxy(Thing thing   ) {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(thing);
		//proxyFactoryBean.setInterceptorNames("myLoggingAdvisor"); //name resolution failed
		//proxyFactoryBean.addAdvice(new MyLoggingAdvice()); //ok
		proxyFactoryBean.addAdvisor(new DefaultIntroductionAdvisor(new MyLoggingAdvice()));//ok
		proxyFactoryBean.addAdvisor(new DefaultIntroductionAdvisor(new UppercaseInterceptor()));//ok
        proxyFactoryBean.setInterfaces(Thing.class); 
		return (Thing) proxyFactoryBean.getObject();
	}

}

/*

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

	<bean id="basicThing" class="tp.appliSpring.exemple_advisor.BasicThing">
		<property name="name" value="nb_continents" />
		<property name="value" value="5" />
	</bean>

	<bean id="myLoggingAdvice" class="tp.appliSpring.exemple_advisor.MyLoggingAdvice" />
	<bean id="uppercaseInterceptor" class="tp.appliSpring.exemple_advisor.UppercaseInterceptor" />

	<bean id="basicThingProxy" 
                class="org.springframework.aop.framework.ProxyFactoryBean">

		<property name="target" ref="basicThing" />

		<property name="interceptorNames">
			<list>
				<value>basicThingProxy</value>
				<value>uppercaseInterceptor</value>
			</list>
		</property>
	</bean>
</beans>

 */

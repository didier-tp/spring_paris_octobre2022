package tp.appliSpring.exemple_advisor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SimpleAopTestApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext springContext = new
				AnnotationConfigApplicationContext(MyOldAopConfig.class) ;
		//Thing chose = (Thing) springContext.getBean("basicThing");
		Thing chose = (Thing) springContext.getBean("basicThingProxy");
		System.out.println(chose.getName() + ":" + chose.getValue());
		System.out.println("chose=" + chose.toString());
		springContext.close();
	}

}

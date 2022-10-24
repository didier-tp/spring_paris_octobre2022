package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//NB: @SpringBootApplication est un équivalent
//de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package
//    + ...

@SpringBootApplication
public class AppliSpringApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringApplication.class, args);
		SpringApplication app = new SpringApplication(AppliSpringApplication.class);
		//app.setAdditionalProfiles("remoteDB","reInit","dev","withSecurity"); //EN PHASE DE DEV SEULEMENT et .bat en prod
		app.setAdditionalProfiles("embeddedDB","reInit","dev");
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8080/appliSpring");
		//http://localhost:8080/appliSpring/index.html
		// et index.html sera trouvé dans src/main/resources/static
	}

}

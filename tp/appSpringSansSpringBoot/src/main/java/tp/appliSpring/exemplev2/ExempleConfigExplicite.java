package tp.appliSpring.exemplev2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/*
 * classe de configuration de composants "spring" (remplace les anciens fichiers xml)
 */

@Configuration
@PropertySource("classpath:/exemples.properties") 
public class ExempleConfigExplicite {
	
	@Value("${exemple.calculateur}")
	private String calculateurClassName;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer
	 propertySourcesPlaceholderConfigurer(){
			return new PropertySourcesPlaceholderConfigurer(); 
			//pour pouvoir interpréter ${} in @Value()
	}

	
	@Bean //pour que la chose construite soit prise en charge par Spring
    public MonAfficheur monAfficheur() {
   	 return new MonAfficheurV1();
   	 //ou bien 
		// return new MonAfficheurV2();
    }
	
	@Bean //pour que la chose construite soit prise en charge par Spring
    public MonCalculateur monCalculateur() {
		System.out.println("calculateurClassName="+calculateurClassName);
		if(this.calculateurClassName.equals("tp.appliSpring.exemplev2.MonCalculateurCarre"))
   	           return new MonCalculateurCarre();
		else if(this.calculateurClassName.equals("tp.appliSpring.exemplev2.MonCalculateurDouble"))
	           return new MonCalculateurDouble();
		else return null;
    }

	 
	 @Bean(initMethod="initialiser")
	 public Coordinateur coordinateur(MonAfficheur monAfficheur,
			                          MonCalculateur monCalculateur) {
		
		 Coordinateur coordinateur = new Coordinateur();
		 coordinateur.setMonAfficheur(monAfficheur); //injection via setter
		 coordinateur.setMonCalculateur(monCalculateur); //injection via setter
		 return coordinateur;
	 }
	 
	 @Bean
	 public CoordinateurAvecInjectionParConstructeur coordinateurAvecInjectionParConstructeur(
			      MonAfficheur monAfficheur , MonCalculateur monCalculateur) {
		//injection par constructeur:
		 return new CoordinateurAvecInjectionParConstructeur(monAfficheur, monCalculateur); 
	 }
	 
}

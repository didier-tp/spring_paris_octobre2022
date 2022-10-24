package tp.appliSpring.exemplev2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * classe de configuration de composants "spring" (remplace les anciens fichiers xml)
 */

@Configuration
public class ExempleConfigExpliciteAvecProfils {
	
	@Bean 
	@Profile("V1")
    public MonAfficheur monAfficheurV1() {
   	 return new MonAfficheurV1();
    }
	
	@Bean 
	@Profile("V2")
    public MonAfficheur monAfficheurV2() {
   	 return new MonAfficheurV2();
    }
	
	@Bean //pour que la chose construite soit prise en charge par Spring
    public MonCalculateur monCalculateur() {
   	// return new MonCalculateurCarre();
   	 //ou bien 
	return new MonCalculateurDouble();
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

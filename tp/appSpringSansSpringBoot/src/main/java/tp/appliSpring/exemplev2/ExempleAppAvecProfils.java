package tp.appliSpring.exemplev2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleAppAvecProfils {

	public static void main(String[] args) {
		//System.setProperty("spring.profiles.active", "V1");
		//System.setProperty("spring.profiles.active", "V1,autresProfilsComplementaires");
		System.setProperty("spring.profiles.active", "V2");
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfigExpliciteAvecProfils.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//qui est initialisé selon la ou les classes de configurations.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		System.out.println("4*4="+monCalculateur.calculer(4));//4*4=16.0 ou autre 
		
		
		//le name/id "coordinateur" coorespond ici au nom de la méthode préfixée par @Bean dans ExempleConfig
		//Coordinateur coordinateurAPrisEnChargeParSpring = (Coordinateur) contextSpring.getBean("coordinateur");
		Coordinateur coordinateurAPrisEnChargeParSpring = contextSpring.getBean(Coordinateur.class);
		coordinateurAPrisEnChargeParSpring.calculerEtAfficher();
		
		
		CoordinateurAvecInjectionParConstructeur coordinateurBPrisEnChargeParSpring = 
				      contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateurBPrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}

}

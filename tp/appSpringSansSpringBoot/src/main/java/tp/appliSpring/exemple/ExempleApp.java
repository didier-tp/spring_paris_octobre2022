package tp.appliSpring.exemple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleApp {

	public static void main(String[] args) {
		ApplicationContext contextSpring = new AnnotationConfigApplicationContext(ExempleConfig.class);
		//contextSpring représente un ensemble de composants pris en charge par spring
		//qui est initialisé selon la ou les classes de configurations.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		System.out.println("4*4="+monCalculateur.calculer(4));//4*4=16.0 ou autre 
		
		/*
		//le name/id "coordinateur" coorespond ici au nom de la méthode préfixée par @Bean dans ExempleConfig
		//Coordinateur coordinateurPrisEnChargeParSpring = (Coordinateur) contextSpring.getBean("coordinateur");
		Coordinateur coordinateurPrisEnChargeParSpring = contextSpring.getBean(Coordinateur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		*/
		
		CoordinateurAvecInjectionParConstructeur coordinateurPrisEnChargeParSpring = 
				      contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();
	}

}

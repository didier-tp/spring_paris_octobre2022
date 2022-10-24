package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
//@Component("monAfficheurV2") //par defaut nom du bean = nom de la classe mais avec minuscule au debut 
public class MonAfficheurV2 implements MonAfficheur {

	public void afficher(String message) {
		System.out.println("** "+message);
	}

	public void afficherMaj(String message) {
		System.out.println("** "+message.toUpperCase());
	}

}

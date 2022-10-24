package tp.appliSpring.exemple;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * objet pris en charge par spring qui va 
 * coordonner :
 *    - un calcul (délégué à un calculateur en v2)
 *    - un affichage_du_resultat (délégué à un objet MonAfficheur lui meme en version V1 ou V2)
 */

@Component
public class CoordinateurAvecInjectionParConstructeur {
	
	
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	
	private MonCalculateur monCalculateur;//référence vers calculateur à injecter
	
	public void calculerEtAfficher() {
		//v1 : calcul en direct
		//v2 : calcul à déléguer à MonCalculateur
		double x=4;
		//double res = x*x;
		double  res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
	
	//@Autowired facultatif/implicite si un seul constructeur servant à effectuer de l'injection
	public CoordinateurAvecInjectionParConstructeur(@Qualifier("monAfficheurV1") MonAfficheur monAfficheur,
			                                        MonCalculateur monCalculateur) {
		super();
		this.monAfficheur = monAfficheur;
		this.monCalculateur= monCalculateur;
		System.out.println("CoordinateurAvecInjectionParConstructeur");
	}

    /*
	public CoordinateurAvecInjectionParConstructeur() {
		super();
	}
	*/
	
	
	
     

}

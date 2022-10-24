package tp.appliSpring.exemplev2;


import org.springframework.stereotype.Component;

/*
 * objet pris en charge par spring qui va 
 * coordonner :
 *    - un calcul (délégué à un calculateur )
 *    - un affichage_du_resultat (délégué à un objet MonAfficheur lui meme en version V1 ou V2)
 */

@Component
public class CoordinateurAvecInjectionParConstructeur {
	
	
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	
	private MonCalculateur monCalculateur;//référence vers calculateur à injecter
	
	public void calculerEtAfficher() {
		double x=4;
		double  res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
	
	//constructeur servant à effectuer de l'injection
	public CoordinateurAvecInjectionParConstructeur(MonAfficheur monAfficheur,
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

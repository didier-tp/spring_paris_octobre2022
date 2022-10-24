package tp.appliSpring.exemplev2;


import javax.annotation.PostConstruct;

/*
 * objet pris en charge par spring qui va 
 * coordonner :
 *    - un calcul (délégué à un calculateur)
 *    - un affichage_du_resultat (délégué à un objet MonAfficheur)
 */


public class Coordinateur {
	
	
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	
	private MonCalculateur monCalculateur;
	
	public void calculerEtAfficher() {
		double x=4;
		double  res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
	
	public Coordinateur() {
		System.out.println("dans le constructeur Coordinateur , monAfficheur="+monAfficheur);
	}

	
	//@PostConstruct 
	public void initialiser() {
		System.out.println("dans initialiser(), monAfficheur="+monAfficheur);
	}

	//setter pour injection via xml ou config java explicite:
	public void setMonAfficheur(MonAfficheur monAfficheur) {
		this.monAfficheur = monAfficheur;
	}

	//setter pour injection via xml ou config java explicite:
	public void setMonCalculateur(MonCalculateur monCalculateur) {
		this.monCalculateur = monCalculateur;
	}
	
     
}

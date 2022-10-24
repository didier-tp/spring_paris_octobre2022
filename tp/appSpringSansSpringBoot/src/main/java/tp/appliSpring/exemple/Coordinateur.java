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
public class Coordinateur {
	
	@Autowired
	//pour demander à spring d'intialiser la référence monAfficheur
	//en pointant sur un composant Spring existant compatible avec le type MonAfficheur
	@Qualifier("monAfficheurV2") //ou @Qualifier("monAfficheurV1")
	
	//@Resource(name="monAfficheurV1")
	private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
	
	/*
	 @Autowired effectue une injection par correspondance de type , si par correspondance de nom besoin complement @Qualifier
	 @Resource effectue une injection par correspondance de nom (si précisé) , sinon par correpondance de type
	 @Inject necessite un ajout dans pom.xml et est interprété comme @Autowired
	 */
	
	@Autowired  //annotation specifique spring pour injection de dépendance
	//@Resource  //vielle annotation standardisée de JEE (simple )
	//@Inject    //annotation de JEE/DI/CDI (standard plus récent que @Resource mais plus complexe)
	private MonCalculateur monCalculateur;
	
	public void calculerEtAfficher() {
		//v1 : calcul en direct
		//v2 : calcul à déléguer à MonCalculateur
		double x=4;
		//double res = x*x;
		double  res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
		monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
	}
	
	public Coordinateur() {
		System.out.println("dans le constructeur Coordinateur , monAfficheur="+monAfficheur);
	}

	public Coordinateur(MonAfficheur monAfficheur) {
		super();
		this.monAfficheur = monAfficheur;
	}
	
	@PostConstruct  //ressemble à ngOnInit() de angular
	public void initialiser() {
		System.out.println("dans initialiser() prefixée par  @PostConstruct, monAfficheur="+monAfficheur);
	}

	public void setMonAfficheur(MonAfficheur monAfficheur) {
		this.monAfficheur = monAfficheur;
	}
	
	
	
     

}

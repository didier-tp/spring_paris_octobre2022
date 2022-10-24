package tp.appliSpring.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tp.appliSpring.experimentation.Afficheur;
import tp.appliSpring.experimentation.Traducteur;
import tp.appliSpring.experimentation.TraducteurFrancaisAnglais;
import tp.appliSpring.experimentation.TraducteurFrancaisEspagnol;

@Configuration  //classe qui configure d'autres classes (de composant)
public class TraducteurConfig {
	
	//@Value("${traducteur.class}") //pour lire la valeur de traducteur.class=... dans application.properties
	@Value("${traducteur.class:TraducteurFrancaisAnglais}") //avec valeur par défaut si pas trouvée dans application.properties
	private String traducteurClasse; //TraducteurFrancaisAnglais ou bien TraducteurFrancaisEspagnol
	
	@Bean //la chose construite sera vue comme un composant spring
	public Traducteur traducteurPourSpring() {
		if(traducteurClasse.equals("TraducteurFrancaisEspagnol"))
			return new TraducteurFrancaisEspagnol();
		else
		     return new TraducteurFrancaisAnglais();
	}
	
	@Bean //la chose construite sera vue comme un composant spring
	public Afficheur AfficheurPourSpring(Traducteur traducteur) {
		return new Afficheur(traducteur);
	
	}

}

package tp.appliSpring.experimentation;

public class Afficheur {
	
	private Traducteur traducteur;

	public Afficheur(Traducteur traducteur) {
		super();
		this.traducteur = traducteur;
		System.out.println("traduction de vert=" + traducteur.traduire("vert"));
	}
	

}

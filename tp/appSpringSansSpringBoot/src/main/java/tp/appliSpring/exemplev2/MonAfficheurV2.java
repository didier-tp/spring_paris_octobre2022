package tp.appliSpring.exemplev2;

public class MonAfficheurV2 implements MonAfficheur {

	public void afficher(String message) {
		System.out.println("** "+message);
	}

	public void afficherMaj(String message) {
		System.out.println("** "+message.toUpperCase());
	}

}

package tp.appliSpring.experimentation;

public class TraducteurFrancaisAnglais implements Traducteur{

	@Override
	public String traduire(String message) {
		switch(message) {
		case "bonjour":
			return "hello";
		case "vert":
			return "green";
		default :
			return "unknown";
		}
	}

}

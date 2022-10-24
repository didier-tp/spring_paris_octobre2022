package tp.appliSpring.experimentation;

public class TraducteurFrancaisEspagnol implements Traducteur{

	@Override
	public String traduire(String message) {
		switch(message) {
		case "bonjour":
			return "Buenos dias";
		case "vert":
			return "verde";
		default :
			return "desconocido";
		}
	}

}

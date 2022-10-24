package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
//@Lazy
public class MonCalculateurCarre implements MonCalculateur {

	public double calculer(double x) {
		return x*x;
	}

}

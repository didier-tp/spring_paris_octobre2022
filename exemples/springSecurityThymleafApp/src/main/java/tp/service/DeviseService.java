package tp.service;

import java.util.List;

import tp.dto.Devise;

public interface DeviseService {
	
	double convertir(double montant, String codeMsource, String codecible);
	List<Devise> listeDevises();
	//...

}

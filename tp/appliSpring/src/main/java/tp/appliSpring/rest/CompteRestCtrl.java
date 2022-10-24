package tp.appliSpring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;
import tp.appliSpring.dto.CompteDetaille;
import tp.appliSpring.dto.CompteEssentiel;
import tp.appliSpring.util_dto.DtoConverter;

@RestController  //composant spring de type "RestController"
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = { "http://localhost:4200" , "http://www.partenaire-particulier.com" })
//@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST , RequestMethod.PUT , RequestMethod.DELETE , RequestMethod.OPTIONS})
@RequestMapping(value="/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired
	private ServiceCompte serviceCompte;
	

	//URL: http://localhost:8080/appliSpring/api-bank/compte/1
	@GetMapping("/{numero}")
	public CompteEssentiel getCompteByNum(@PathVariable("numero")Long num) {
		return DtoConverter.compteToCompteEssentiel( serviceCompte.rechercherCompteParNumero(num));
	}
	
	//URL: http://localhost:8080/appliSpring/api-bank/compte/avecDetails/1
	@GetMapping("/avecDetails/{numero}")
	public CompteDetaille getCompteDetailleByNum(@PathVariable("numero")Long num) {
			return DtoConverter.compteToCompteDetaille( serviceCompte.rechercherCompteAvecOperationsParNumero(num));
	}
	
	//URL:      http://localhost:8080/appliSpring/api-bank/compte
	// ou bien  http://localhost:8080/appliSpring/api-bank/compte?numClient=1
	@GetMapping("")
	public List<CompteEssentiel> getComptesByCriteria(@RequestParam(name="numClient",required=false)Long numClient) {
         if(numClient!=null)
        	 return DtoConverter.comptesToComptesEssentiels(serviceCompte.rechercherComptesPourClient(numClient));
         else
        	 return DtoConverter.comptesToComptesEssentiels(serviceCompte.rechercherTousLesComptes());
	}
	
	// http://localhost:8080/appliSpring/api-bank/compte appelée en mode POST
	//avec { "numero" : null , "label" : "CompteXy" , "solde" : 50.0 }
	// ou bien  {  "label" : "CompteXy" , "solde" : 50.0 }
	// à tester avec PostMan ou bien un équivalent
	@PostMapping("")
	public CompteEssentiel postCompte(@RequestBody CompteEssentiel compteEssentiel) {
		Compte compteSauvegarde = serviceCompte.sauvegarderNouveauCompte(
				                 DtoConverter.compteEssentielToCompte(compteEssentiel));
		return DtoConverter.compteToCompteEssentiel(compteSauvegarde);
		//on retourne le compte avec son numero auto-incrémenté
	}
}

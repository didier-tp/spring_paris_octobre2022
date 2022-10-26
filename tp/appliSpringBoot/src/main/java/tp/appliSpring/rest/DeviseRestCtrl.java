package tp.appliSpring.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.core.entity.Devise;
import tp.appliSpring.core.service.DeviseService;
import tp.appliSpring.dto.Currency;
import tp.appliSpring.dto.MessageGenerique;


@RestController
@RequestMapping(value="/api-bank/devise" , headers="Accept=application/json")
public class DeviseRestCtrl {
	
	private DeviseService deviseService;
	

	/*@Autowired*/
	public DeviseRestCtrl(DeviseService deviseService) {
		//injection de dépendance par constructeur
		this.deviseService = deviseService;
	
	}
	 

	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//http://localhost:8080/appliSpring/api-bank/devise/EUR
	@GetMapping(value="/{codeDevise}")
	public Currency getDeviseByName(@PathVariable("codeDevise") String codeDevise) {
	    Devise devise = deviseService.rechercherDeviseParCode(codeDevise);
	    
	    //conversion entity vers dto via constructeur
	    return new Currency(devise.getCode(),devise.getNom(),devise.getChange());
	    
	    //BeanUtils.copyProperties(sourceObj, targetObj)
	}
	
	//RECHERCHE MULTIPLE :
	//http://localhost:8080/appliSpring/api-bank/devise
	@GetMapping(value="" )
	public List<Currency> getDevisesByCriteria(){
	    return deviseListToCurrencyList(
	    		deviseService.rechercherToutesDevises());
	}
	
	//POST
	//http://localhost:8080/appliSpring/api-bank/devise
	//avec { "code" : "DDK" , "name" : "couronne danoise" , "rate" : 7.77 }
	//dans partie body de la requête entrante
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value="" )
	public ResponseEntity<?> postDevise(@RequestBody Currency c){
		   String codeDevise = c.getCode();
		   if(deviseService.existeOuPas(codeDevise)) {
			   /*
			   Map<String,Object> msgErreur = new HashMap<>();
			   msgErreur.put("message", "autre devise avec meme code dejà existante");
			   return new ResponseEntity<Map<String,Object>>
			                    (msgErreur, HttpStatus.CONFLICT);
			   */
			   return new ResponseEntity<MessageGenerique>
                      (new MessageGenerique("autre devise avec meme code dejà existante") , 
                       HttpStatus.CONFLICT);
		   }else {
		      deviseService.sauvegarderDevise(
		    		   new Devise(c.getCode(),c.getName(),c.getRate()));
		      return new ResponseEntity<Currency>(c, HttpStatus.CREATED);
		   }
	}
	
	//PUT
	//http://localhost:8080/appliSpring/api-bank/devise
	//avec { "code" : "DDK" , "name" : "couronne du danemark" , "rate" : 8.88 }
	//dans partie body de la requête entrante
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value="" )
	public ResponseEntity<?> putDevise(@RequestBody Currency c){
		 String codeDevise = c.getCode();
		   if(deviseService.existeOuPas(codeDevise)) {		
			   deviseService.sauvegarderDevise(
					      new Devise(c.getCode(),c.getName(),c.getRate()));
			   return new ResponseEntity<Currency>(c, HttpStatus.OK);
		   }else {
			  return new ResponseEntity<MessageGenerique>
               (new MessageGenerique("pas de devise existante a mettre a jour","pour code="+codeDevise) , 
                HttpStatus.NOT_FOUND);		   
		   }
	}
	
	//DELETE
	//http://localhost:8080/appliSpring/api-bank/devise/DDK
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value="/{codeDevise}" )
	public ResponseEntity<?> deleteDevise(
			     @PathVariable("codeDevise") String codeDevise){
		   if(deviseService.existeOuPas(codeDevise)) {
			   this.deviseService.supprimerDeviseParCode(codeDevise);
			   Map<String,Object> msgOk = new HashMap<>();
			   msgOk.put("message", "suppression bien effectuee");
			   return new ResponseEntity<Map<String,Object>>(msgOk, HttpStatus.OK);
		   }else {
				  return new ResponseEntity<MessageGenerique>
	               (new MessageGenerique("pas de devise existante a supprimer","pour code="+codeDevise) , 
	                HttpStatus.NOT_FOUND);		   
			   }	   
	}
	
	public static List<Currency> deviseListToCurrencyList(List<Devise> listeDevise){
		List<Currency> listeCurrency = new ArrayList<>();
		for(Devise d : listeDevise) {
			listeCurrency.add(new Currency(d.getCode(),d.getNom(),d.getChange()));
		}
		return listeCurrency; 
	}
	

}

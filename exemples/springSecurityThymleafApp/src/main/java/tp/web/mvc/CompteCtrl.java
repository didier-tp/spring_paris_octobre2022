package tp.web.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.NoArgsConstructor;
import tp.entity.Client;
import tp.entity.Compte;
import tp.service.ClientService;
import tp.service.CompteService;

@Controller 
//avec session
@NoArgsConstructor
@SessionAttributes( value={"numClient","listeCpt"} ) //noms des "modelAttributes" qui sont EN PLUS récupérés/stockés en SESSION HTTP
                //au niveau de la page de rendu --> visibles en requestScope ET en sessionScope
public class CompteCtrl {
	
	//pour  th:object="${client}" dans nouveauClient.html
    @ModelAttribute("client")
	public Client addClientAttributeInModel() {
	        return new Client();
	}

    @Autowired
	private ClientService clientService ;
	
	@Autowired
	private CompteService compteService ;
	
	@PreAuthorize("hasRole('CUSTOMER')") //check if ROLE_CUSTOMER
	@RequestMapping(value="/afterLogin" )
    public String doLogin(Model model,@RequestParam(name="numClient",required = false)Long numClient) {
	    if(numClient==null) {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	if (principal instanceof UserDetails) {
	    	   String username = ((UserDetails)principal).getUsername();
	    	   numClient = Long.parseLong(username);
	    	}
	    }
	    List<Compte> listeCpt = this.compteService.comptesDuClient(numClient);
	    if(listeCpt!=null && !listeCpt.isEmpty()){
	          model.addAttribute("numClient",numClient);
              model.addAttribute("listeCpt",listeCpt);
              model.addAttribute("title","comptes");
	    }
        return "comptes"; 
    }
	
	@RequestMapping("/to-nouveauClient")
    public String toNouveauClient(){
	   return "nouveauClient"; 
    }
	
	@RequestMapping(value="/nouveauClient" )
    public String nouveauClient(Model model,
    		@ModelAttribute("client") Client client) {
	 
		this.clientService.nouveauClientAvecNouveauxComptesParDefaut(client);
		model.addAttribute("message","nouveau client enregistré qui doit maintenant se connecter pour accéder aux comptes");
        model.addAttribute("title","welcome");
        return "welcome"; 
    }
}

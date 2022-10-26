package tp.appliSpring.reinit;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.core.dao.DaoClient;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Devise;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.core.service.DeviseService;

@Component
@Profile("reInit")
public class ReInitDefaultDataSet {
	
	@Autowired
	private DaoCompte daoCompte;
	
	@Autowired
	private DaoClient daoClient;
	
	@Autowired
	private DaoOperation daoOperation;
	
	@Autowired
	private DeviseService deviseService;

	
	@PostConstruct
	public void initDataSet() {
		Client client1 = new Client(null,"luc","Dupond");
		Compte compteC1a = this.daoCompte.save(new Compte(null,"compteC1a",100.0));
		Operation op1CompteC1a = new Operation(null,-50.0,"achat courses" , new Date() ,compteC1a );
		daoOperation.save(op1CompteC1a);
		client1.addCompte(compteC1a);
		client1.addCompte(this.daoCompte.save(new Compte(null,"compteC1b",50.0)));
		client1 = daoClient.save(client1);
		
		Client client2 = new Client(null,"jean","Durand");
		client2.addCompte(this.daoCompte.save(new Compte(null,"compteC2a",80.0)));
		client2.addCompte(this.daoCompte.save(new Compte(null,"compteC2b",60.0)));
		client2 = daoClient.save(client2);
		
		Devise deviseEuro =  new Devise("EUR","euro",1.0);
		deviseService.sauvegarderDevise(deviseEuro);
		
		deviseService.sauvegarderDevise(new Devise("USD","dollar",1.1));
		deviseService.sauvegarderDevise(new Devise("GBP","livre",0.9));
		deviseService.sauvegarderDevise(new Devise("JPY","yen",120.0));
	}

}

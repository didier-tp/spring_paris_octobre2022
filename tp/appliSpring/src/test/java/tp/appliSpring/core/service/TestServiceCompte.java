package tp.appliSpring.core.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.dao.DaoClient;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest
@ActiveProfiles({ "embeddedDB" })
//@ActiveProfiles({ "remoteDB" , "perf" , "dev" })
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);
	
	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	@Autowired
	private DaoClient daoClient; //pour aider à tester
	//private ServiceClient ServiceClient; //pour aider à tester
	
	@Test
	public void testRechercherCompteParNumero() {
		Compte compte = new Compte(null,"compteC1",100.0);
		Compte compteSauvegarde = this.serviceCompte.sauvegarderNouveauCompte(compte); //INSERT INTO
		logger.debug("compteSauvegarde=" + compteSauvegarde);
		
		Compte compteRelu = this.serviceCompte.rechercherCompteParNumero(compteSauvegarde.getNumero()); //SELECT
		Assertions.assertEquals("compteC1",compteRelu.getLabel());
		Assertions.assertEquals(100.0,compteRelu.getSolde());
		logger.debug("compteRelu=" + compteRelu);
		
	}
	
	@Test
	public void testRechercherComptesPourClient() {
		Client client1 = new Client(null,"luc","Dupond");
		client1.addCompte(this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteC1a",100.0)));
		client1.addCompte(this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteC1b",50.0)));
		client1 = daoClient.save(client1);
		
		Client client2 = new Client(null,"jean","Durand");
		client2.addCompte(this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteC2a",80.0)));
		client2.addCompte(this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteC2b",60.0)));
		client2 = daoClient.save(client2);
		
		List<Compte> comptesDuClient1 = this.serviceCompte.rechercherComptesPourClient(client1.getNumero());	
		logger.debug("comptesDuClient1:"+comptesDuClient1);
        Assertions.assertTrue(comptesDuClient1.get(0).getLabel().equals("compteC1a")
        		             || comptesDuClient1.get(0).getLabel().equals("compteC1b"));
		Assertions.assertTrue(comptesDuClient1.size()==2);
	}
	
	@Test
	public void testVirement() {
		Compte compteASauvegarde = this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteA",300.0));
		Compte compteBSauvegarde = this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteB",100.0));
		long numCptA = compteASauvegarde.getNumero();
		long numCptB = compteBSauvegarde.getNumero();		
		//remonte en memoire les anciens soldes des compte A et B avant virement (+affichage console ou logger)
		double soldeA_avant= compteASauvegarde.getSolde();
		double soldeB_avant = compteBSauvegarde.getSolde();
		logger.debug("avant bon virement, soldeA_avant="+soldeA_avant + " et soldeB_avant=" + soldeB_avant);
		//effectuer un virement de 50 euros d'un compte A vers vers compte B
		this.serviceCompte.effectuerVirement(50.0, numCptA, numCptB);
		//remonte en memoire les nouveaux soldes des compte A et B apres virement (+affichage console ou logger)
		/*
		Compte compteAReluApresVirement = this.serviceCompte.rechercherCompteParNumero(numCptA);
		Compte compteBReluApresVirement = this.serviceCompte.rechercherCompteParNumero(numCptB);
		*/
		Compte compteAReluApresVirement = this.serviceCompte.rechercherCompteAvecOperationsParNumero(numCptA);
		Compte compteBReluApresVirement = this.serviceCompte.rechercherCompteAvecOperationsParNumero(numCptB);
		double soldeA_apres = compteAReluApresVirement.getSolde();
		double soldeB_apres = compteBReluApresVirement.getSolde();
		logger.debug("apres bon virement, soldeA_apres="+soldeA_apres + " et soldeB_apres=" + soldeB_apres);
		logger.debug("apres bon virement, operations sur compte A: "+compteAReluApresVirement.getOperations());
		logger.debug("apres bon virement, operations sur compte B: "+compteBReluApresVirement.getOperations());
		//verifier -50 et +50 sur les différences de soldes sur A et B
		Assertions.assertEquals(soldeA_avant - 50, soldeA_apres,0.000001);
		Assertions.assertEquals(soldeB_avant + 50, soldeB_apres,0.000001);
	}
	
	@Test
	public void testMauvaisVirement() {
		Compte compteASauvegarde = this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteA",300.0));
		Compte compteBSauvegarde = this.serviceCompte.sauvegarderNouveauCompte(new Compte(null,"compteB",100.0));
		long numCptA = compteASauvegarde.getNumero();
		long numCptB = compteBSauvegarde.getNumero();		
		//remonte en memoire les anciens soldes des compte A et B avant virement (+affichage console ou logger)
		double soldeA_avant= compteASauvegarde.getSolde();
		double soldeB_avant = compteBSauvegarde.getSolde();
		logger.debug("avant mauvais virement, soldeA_avant="+soldeA_avant + " et soldeB_avant=" + soldeB_avant);
		//effectuer un virement de 50 euros d'un compte A vers vers compte B
		try {
			this.serviceCompte.effectuerVirement(50.0, numCptA, -numCptB); //erreur volontaire
		} catch (Exception e) {
			logger.error("echec normal du virement " + e.getMessage());
		}
		//remonte en memoire les nouveaux soldes des compte A et B apres virement (+affichage console ou logger)
		Compte compteAReluApresVirement = this.serviceCompte.rechercherCompteParNumero(numCptA);
		Compte compteBReluApresVirement = this.serviceCompte.rechercherCompteParNumero(numCptB);
		double soldeA_apres = compteAReluApresVirement.getSolde();
		double soldeB_apres = compteBReluApresVirement.getSolde();
		logger.debug("apres mauvais virement, soldeA_apres="+soldeA_apres + " et soldeB_apres=" + soldeB_apres);
		//verifier -50 et +50 sur les différences de soldes sur A et B
		Assertions.assertEquals(soldeA_avant , soldeA_apres,0.000001);
		Assertions.assertEquals(soldeB_avant , soldeB_apres,0.000001);
	}

}

package tp.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.MySpringBootApplication;
import tp.entity.Client;
import tp.entity.Compte;
import tp.service.ClientService;
import tp.service.CompteService;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
public class TestServiceCompte {
	
	@Autowired
	private CompteService serviceCompte; //à tester
	
	
	@Autowired
	private ClientService serviceClient; //pour aider à tester
	
	@Test
	public void testBonTransfert() {
		Compte c1 = new Compte(null,"compte1",250.0);
		serviceCompte.sauvegarderCompte(c1);
		Compte c2 = new Compte(null,"compte2",350.0);
		serviceCompte.sauvegarderCompte(c2);
		
		serviceCompte.transferer(c1.getNumero(), c2.getNumero(),50);
		
		Compte c1Apres = serviceCompte.rechercherCompteParNumero(c1.getNumero());
		Compte c2Apres = serviceCompte.rechercherCompteParNumero(c2.getNumero());
		
		System.out.println("Apres bon virement , c1="+c1Apres+",c2="+c2Apres);
		Assertions.assertEquals(c1.getSolde()-50, c1Apres.getSolde(),0.0001);
		Assertions.assertEquals(c2.getSolde()+50, c2Apres.getSolde(),0.0001);
	}
	
	@Test
	public void testMauvaisTransfertAvecTransactional() {
		Compte c1 = new Compte(null,"compte1",250.0);
		serviceCompte.sauvegarderCompte(c1);
		Compte c2 = new Compte(null,"compte2",350.0);
		serviceCompte.sauvegarderCompte(c2);
		try {
			//appel avec un numCptCred négatif qui n'existe pas
			serviceCompte.transferer(c1.getNumero(), -c2.getNumero(),50);
		} catch (Exception e) {
			System.out.println("Exception attendue :" + e.getMessage());
		}
		
		Compte c1Apres = serviceCompte.rechercherCompteParNumero(c1.getNumero());
		Compte c2Apres = serviceCompte.rechercherCompteParNumero(c2.getNumero());
		
		System.out.println("Apres mauvais virement , c1="+c1Apres+",c2="+c2Apres);
		Assertions.assertEquals(c1.getSolde()-0, c1Apres.getSolde(),0.0001);
		Assertions.assertEquals(c2.getSolde()+0, c2Apres.getSolde(),0.0001);
	}
	
	@Test
	public void testRechercherComptesDuClient() {
		//créer un client
		Client cliA = new Client(null,"Bon", "Jean");
		serviceClient.sauvegarderClient(cliA);
		
		//créer 3 comptes dont 2 rattachés au client
		Compte cA = new Compte(null,"compteA",50.0);
		cA.setClient(cliA); serviceCompte.sauvegarderCompte(cA);
		Compte cB = new Compte(null,"compteB",150.0);
		cB.setClient(cliA); serviceCompte.sauvegarderCompte(cB);
		Compte cC = new Compte(null,"compteC",250.0);
		serviceCompte.sauvegarderCompte(cC);
		
		//rechercher les comptes rattachés au client , normalement 2
		List<Compte> comptes = serviceCompte.comptesDuClient(cliA.getNumero());
		Assertions.assertTrue(comptes.size()==2);
		for(Compte c : comptes) {
			System.out.println(c);
		}
	}
	

}

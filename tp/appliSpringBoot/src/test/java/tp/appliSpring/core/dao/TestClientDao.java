package tp.appliSpring.core.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tp.appliSpring.core.dao.DaoClient;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;

//@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(/*classes= {AppliSpringApplication.class}*/)
//@ActiveProfiles({ "embeddedDB" }) //pour analyser le fichier application-embeddedDB.proporties (H2)
@ActiveProfiles({ "remoteDB" , "dev"}) //pour analyser le fichier application-remote.proporties (MySQL)
public class TestClientDao {
	
    private static Logger logger = LoggerFactory.getLogger(TestClientDao.class);
	
	@Autowired
	private DaoClient daoClient; //à tester
	
	@Autowired
	private DaoCompte daoCompte; //pour aider à écrire le test
	
	@Test
	public void testFindClientByIdWithComptes() {
		Client client1 = new Client(null,"luc","Dupond");
		client1.addCompte(this.daoCompte.save(new Compte(null,"compteC1a",100.0)));
		client1.addCompte(this.daoCompte.save(new Compte(null,"compteC1b",50.0)));
		client1 = daoClient.save(client1);
		
		Client client2 = new Client(null,"jean","Durand");
		client2.addCompte(this.daoCompte.save(new Compte(null,"compteC2a",80.0)));
		client2.addCompte(this.daoCompte.save(new Compte(null,"compteC2b",60.0)));
		client2 = daoClient.save(client2);
		
		//Client client1sansSesComptes = this.daoClient.findById(client1.getNumero()).get();
		Client client1avecSesComptes = this.daoClient.findClientByIdWithComptes(client1.getNumero());
		logger.debug("client1avecSesComptes="+client1avecSesComptes);
		for(Compte compte : client1avecSesComptes.getComptes())
			logger.debug("\t"+compte);
		Assertions.assertEquals("Dupond", client1avecSesComptes.getNom());
		Assertions.assertTrue(client1avecSesComptes.getComptes().size()==2);
	}
	
	@Test
	public void testFindByNom() {
		this.daoClient.save(new Client(null,"alex","Therieur"));
		this.daoClient.save(new Client(null,"alain","Therieur"));
		this.daoClient.save(new Client(null,"jean","Bon"));
		this.daoClient.save(new Client(null,"axelle","Aire"));
		this.daoClient.save(new Client(null,"tarte","Tatin"));

		//List<Client> clientsDeNomTherieur= daoClient.findByNom("Therieur");
		//List<Client> clientsDeNomTherieur= daoClient.findByNomIgnoreCase("therieur");
		List<Client> clientsDeNomTherieur= daoClient.findByNomOrderByPrenom("Therieur");
		Assertions.assertTrue(clientsDeNomTherieur.size()>=2);
		logger.debug("clientsDeNomTherieur=" + clientsDeNomTherieur);
		
		List<Client> clientsDeNomEnT= daoClient.findByNomLike("T%"); //nom commençant par T
		Assertions.assertTrue(clientsDeNomEnT.size()>=3);
		logger.debug("clientsDeNomEnT=" + clientsDeNomEnT);
	}
	
	@Test
	public void testAjoutEtRelectureEtSuppression() {
		//hypothese : base avec tables vides au lancement du test
		Client client = new Client(null,"alex","Therieur");
		Client clientSauvegarde = this.daoClient.save(client); //INSERT INTO
		logger.debug("clientSauvegarde=" + clientSauvegarde);
		
		Client clientRelu = this.daoClient.findById(clientSauvegarde.getNumero()).get(); //SELECT
		Assertions.assertEquals("alex",clientRelu.getPrenom());
		Assertions.assertEquals("Therieur",clientRelu.getNom());
		logger.debug("clientRelu=" + clientRelu);
		
		//+supprimer :
		this.daoClient.deleteById(clientSauvegarde.getNumero());
		
		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		Client clientReluApresSuppression = this.daoClient.findById(clientSauvegarde.getNumero()).orElse(null); 
		Assertions.assertTrue(clientReluApresSuppression == null);
	}

}

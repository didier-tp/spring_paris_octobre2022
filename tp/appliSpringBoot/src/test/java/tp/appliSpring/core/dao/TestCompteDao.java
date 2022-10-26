package tp.appliSpring.core.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;


//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(classes= {AppliSpringApplication.class})
//@ActiveProfiles({ "embeddedDB" , "dev" })
@ActiveProfiles({ "remoteDB",  "dev" })
public class TestCompteDao {
	
    private static Logger logger = LoggerFactory.getLogger(TestCompteDao.class);
	
	@Autowired
	private DaoCompte daoCompte; //à tester
	
	
	@Test
	public void testFindBySoldeMin() {
		this.daoCompte.save(new Compte(null,"compteC1",100.0));
		this.daoCompte.save(new Compte(null,"compteC2",-50.0));
		this.daoCompte.save(new Compte(null,"compteC3",-100.0));
		this.daoCompte.save(new Compte(null,"compteC4",200.0));
		this.daoCompte.save(new Compte(null,"compteC5",50.0));
		//List<Compte> comptesSansDecouvert= daoCompte.findBySoldeMin(0);
		List<Compte> comptesSansDecouvert= daoCompte.findBySoldeGreaterThanEqual(0.0);
		Assertions.assertTrue(comptesSansDecouvert.size()>=3);
		logger.debug("comptesSansDecouvert=" + comptesSansDecouvert);
	}
	
	@Test
	public void testAjoutEtRelectureEtSuppression() {
		//hypothese : base avec tables vides au lancement du test
		Compte compte = new Compte(null,"compteA",100.0);
		Compte compteSauvegarde = this.daoCompte.save(compte); //INSERT INTO
		logger.debug("compteSauvegarde=" + compteSauvegarde);
		
		Compte compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).get(); //SELECT
		Assertions.assertEquals("compteA",compteRelu.getLabel());
		Assertions.assertEquals(100.0,compteRelu.getSolde());
		logger.debug("compteRelu=" + compteRelu);
		
		//+supprimer :
		this.daoCompte.deleteById(compteSauvegarde.getNumero());
		
		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		Compte compteReluApresSuppression = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); 
		Assertions.assertTrue(compteReluApresSuppression == null);
	}

}

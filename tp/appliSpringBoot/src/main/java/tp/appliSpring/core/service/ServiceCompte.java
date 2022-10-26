package tp.appliSpring.core.service;

import java.util.List;

import tp.appliSpring.core.entity.Compte;

/*
 ServiceCompte = interface d'un service métier
 avec: méthodes CRUD déléguées au DAO
       méthodes spécifiques au métier (virement, verifierPasDecouvert,...)
       exceptions qui remontent en cas de problème (héritant de RuntimeException)
 */

public interface ServiceCompte {
	Compte rechercherCompteParNumero(long numCompte);
	Compte rechercherCompteAvecOperationsParNumero(long numCompte);
	
	List<Compte> rechercherComptesPourClient(long numClient);
	//...
	void supprimerCompte(long numCompte);
	Compte sauvegarderNouveauCompte(Compte compte);//create /insert into /persist
	void mettreAJourCompte(Compte compte); //update
	
	void effectuerVirement(double montant,long numCptDeb , long numCptCred);
	//...
	List<Compte> rechercherTousLesComptes();
}

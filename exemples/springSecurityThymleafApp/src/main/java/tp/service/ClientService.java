package tp.service;

import tp.entity.Client;

public interface ClientService {
	Client sauvegarderClient(Client client);
	Client rechercherClientParNumero(long numero);
	Client nouveauClientAvecNouveauxComptesParDefaut(Client client);
}

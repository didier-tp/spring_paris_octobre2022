package tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.ClientDao;
import tp.dao.CompteDao;
import tp.entity.Client;
import tp.entity.Compte;

@Service //heritant de @Component
@Transactional
public class ClientServiceImpl implements ClientService {
	
	@Autowired //ou @Inject
	private ClientDao clientDao;
	
	@Autowired
	private CompteDao compteDao ;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Client sauvegarderClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public Client rechercherClientParNumero(long numero) {
		return clientDao.findById(numero).orElse(null);
	}

	@Override
	public Client nouveauClientAvecNouveauxComptesParDefaut(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		Client savedClient = clientDao.save(client);
		Compte cc = new Compte(null,"compte courant client_" + client.getNumero(), 20.0);
		cc.setClient(savedClient);compteDao.save(cc);
		Compte ce = new Compte(null,"compte epargne client_" + client.getNumero(), 10.0);
		ce.setClient(savedClient);compteDao.save(ce);
		return savedClient;
	}

}

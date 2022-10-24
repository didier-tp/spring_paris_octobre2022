package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;

@Repository //@Component de type DAO/Repository
public class DaoClientJpa implements DaoClient {
	
	@PersistenceContext //initialiser entityManager via application.properties au sein d'un projet SpringBoot
	private EntityManager entityManager;

	@Override
	public Client findById(Long numCli) {
		return entityManager.find(Client.class, numCli);
	}

	@Override
	@Transactional
	public Client save(Client client) {
		if(client.getNumero()==null)
			entityManager.persist(client);//INSERT INTO
		else
			entityManager.merge(client);//UPDATE
	return client; //avec numero plus null (auto_incrémenté)
	}

	@Override
	public List<Client> findAll() {
		return entityManager.createQuery("SELECT c FROM Client c",Client.class)
		           .getResultList();
	}

	@Override
	@Transactional
	public void deleteById(Long numCli) {
		Client clientAsupprimer = entityManager.find(Client.class, numCli);
		entityManager.remove(clientAsupprimer);
	}

}

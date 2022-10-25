package tp.dao;

import org.springframework.data.repository.CrudRepository;

import tp.entity.Client;

public interface ClientDao extends CrudRepository<Client,Long>{
   //principales méthodes héritées : findById() , save() , deleteById() , ...

}

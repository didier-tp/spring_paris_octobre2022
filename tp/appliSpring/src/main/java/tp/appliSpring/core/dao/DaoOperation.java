package tp.appliSpring.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Operation;

public interface DaoOperation extends JpaRepository<Operation,Long>{
    /*
    on hérite de plein de méthodes prédéfinies:
    .save() , .findById() , findAll() , deleteById()
    */

}

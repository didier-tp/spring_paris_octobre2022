package tp.appliSpring.core.dao;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Client;
import tp.appliSpring.core.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long>{
    /*
     on hérite de plein de méthodes prédéfinies:
     .save() , .findById() , findAll() , deleteById()
     */
 
   List<Compte> findBySoldeGreaterThanEqual(double soldeMinimum); //bien (avec spring-data) : respecte convention de nommage et requête générée automatiquement
   
   
   List<Compte> findBySoldeMin(double soldeMinimum); //moins bien : ne respecte pas convention de nommage
   //mais peut tout de même être codée via 
   //@NamedQuery(name="Compte.findBySoldeMin", query="SELECT c FROM Compte c WHERE c.solde >= ?1")
   //au dessus de la classe Compte
   
   //methodes spécifiques (avec requetes codées en @NamedQuery ou @Query)
   Compte findCompteByIdWithOperations(Long numCompte);
   
}
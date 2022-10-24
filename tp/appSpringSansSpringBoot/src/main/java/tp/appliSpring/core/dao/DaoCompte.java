package tp.appliSpring.core.dao;

import java.util.List;

import tp.appliSpring.core.entity.Compte;

public interface DaoCompte{
         Compte findById(Long numCpt);
         Compte save(Compte compte); //sauvegarde au sens saveOrUpdate
         List<Compte> findAll();
         void deleteById(Long numCpt);
    //...
}
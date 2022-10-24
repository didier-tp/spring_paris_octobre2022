package tp.appliSpring.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Devise;

public interface DeviseDao extends JpaRepository<Devise,String> {

}

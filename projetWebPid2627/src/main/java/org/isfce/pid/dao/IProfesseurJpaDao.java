package org.isfce.pid.dao;

import org.isfce.pid.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesseurJpaDao extends JpaRepository<Professeur, String> {
		
}

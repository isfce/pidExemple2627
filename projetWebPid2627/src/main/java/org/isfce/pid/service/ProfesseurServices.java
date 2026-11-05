package org.isfce.pid.service;

import java.util.List;
import java.util.Optional;

import org.isfce.pid.dao.IProfesseurJpaDao;
import org.isfce.pid.model.Professeur;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfesseurServices {
	private IProfesseurJpaDao professeurDao;

	public ProfesseurServices(IProfesseurJpaDao professeurDao) {
		this.professeurDao = professeurDao;
	}

	public Optional<Professeur> getById(String id) {
		return professeurDao.findById(id);
	}

	public List<Professeur> findAll() {
		return professeurDao.findAll();
	}

	public boolean existsById(String id) {
		return professeurDao.existsById(id);

	}

	public Professeur save(Professeur professeur) {
		return professeurDao.save(professeur);
	}


}

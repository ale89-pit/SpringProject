package it.tecninf.hrmanagement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.tecninf.hrmanagement.model.Curriculum;
import it.tecninf.hrmanagement.model.Dipendente;

public interface CurriculumRepository extends CrudRepository<Curriculum,Integer>{
	
//	@Query(value = "SELECT * FROM hrmanagement.competenze INNER JOIN hrmanagement.curriculum ON hrmanagement.curriculum.id_curriculum = hrmanagement.competenze.id_curriculum INNER JOIN hrmanagement.dipendente ON hrmanagement.dipendente.id_dipendente = hrmanagement.competenze.id_dipendente INNER JOIN hrmanagement.tipskill on hrmanagement.tipskill.id_tipskill = hrmanagement.competenze.id_tipskill WHERE tipskill.tipologia_skill IN (:skill) ORDER BY cognome ASC",nativeQuery=true)
	@Query(value = "SELECT * FROM hrmanagement.competenze INNER JOIN hrmanagement.curriculum ON hrmanagement.curriculum.id_curriculum = hrmanagement.competenze.id_curriculum INNER JOIN hrmanagement.dipendente ON hrmanagement.dipendente.id_dipendente = hrmanagement.competenze.id_dipendente INNER JOIN hrmanagement.tipskill on hrmanagement.tipskill.id_tipskill = hrmanagement.competenze.id_tipskill WHERE tipskill.tipologia_skill = 'Java' And tipskill.tipologia_skill = 'HTML' And tipskill.tipologia_skill = 'CSS' ORDER BY cognome ASC",nativeQuery=true)
	public List<Curriculum> getCvWhitCompetence();
}

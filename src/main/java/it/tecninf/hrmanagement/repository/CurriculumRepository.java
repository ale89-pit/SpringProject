package it.tecninf.hrmanagement.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.tecninf.hrmanagement.model.Curriculum;

public interface CurriculumRepository extends CrudRepository<Curriculum,Integer>{
	
}

package it.tecninf.hrmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import javax.transaction.Transactional;

import it.tecninf.hrmanagement.model.Competenze;
import it.tecninf.hrmanagement.model.Dipendente;
import it.tecninf.hrmanagement.utility.Chart;


public interface CompetenzeRepository extends CrudRepository<Competenze,Integer>{

	@Query(value = "SELECT COUNT(*) AS quantity FROM hrmanagement.competenze GROUP BY hrmanagement.competenze.id_tipskill;", nativeQuery = true)
	public List<Integer> getChartVals();
	
	@Query(value = "SELECT  hrmanagement.tipskill.tipologia_skill AS nomeskill FROM hrmanagement.competenze INNER JOIN hrmanagement.tipskill ON hrmanagement.competenze.id_tipskill = hrmanagement.tipskill.id_tipskill GROUP BY hrmanagement.competenze.id_tipskill", nativeQuery = true)
	public List<String> getChartNames();
	

	
}

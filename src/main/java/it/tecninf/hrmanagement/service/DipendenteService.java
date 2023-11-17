package it.tecninf.hrmanagement.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tecninf.hrmanagement.dto.DipendenteDto;
import it.tecninf.hrmanagement.model.Curriculum;
import it.tecninf.hrmanagement.model.Dipendente;
import it.tecninf.hrmanagement.model.Tipskill;
import it.tecninf.hrmanagement.repository.CompetenzeRepository;
import it.tecninf.hrmanagement.repository.CurriculumRepository;
import it.tecninf.hrmanagement.repository.DipendenteRepository;
import it.tecninf.hrmanagement.utility.ResponseCurricculum;

@Service
public class DipendenteService {

	private static final Date Date = null;
	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	@Autowired
	private CompetenzeRepository competenzeRepository;

	public List<Dipendente> listaDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAllEmp();
	}

	public List<Dipendente> listaDipendentiVecchi() {
		return (List<Dipendente>) dipendenteRepository.findAllOldEmp();

	}

	public Optional<Dipendente> getByID(int id) {
		return dipendenteRepository.findById(id);
	}

	

	public List<Dipendente> getSkillFilter(String skill) {
		return (List<Dipendente>) dipendenteRepository.getSkillFilter(skill);
	}
	
	public List<DipendenteDto> getDipendentiBetweenDateAndSkill(String danaInizio,String dataFine,String skill){
		List<DipendenteDto> filterDto = new ArrayList<DipendenteDto>();  
		List<Dipendente> filterList = dipendenteRepository.getDipendentiBetweenDateAndSkill(danaInizio, dataFine, skill);
		
		for(Dipendente d : filterList) {
			DipendenteDto dto = new DipendenteDto(d.getNome(),d.getCognome(),d.getSkills());
			filterDto.add(dto);
		}
		return filterDto;
	
	}

	public int lastIdDipendente() {
		return (int) dipendenteRepository.lastIdDipendente();
	}

	public void addDipendente(String matricola, String nome, String cognome, Date data_di_nascita, String indirizzo,
			String citta, int id_ref_nazionalita, int row_exist) {
		dipendenteRepository.addDipendente(matricola, nome, cognome, data_di_nascita, indirizzo, citta,
				id_ref_nazionalita, row_exist);
	}
	
	
	public  Set<Tipskill> getSkillByDip (int idDip) {
		Dipendente dip = dipendenteRepository.getSkillFilterByIdDip(idDip);
		return  dip.getSkills();
	}
	

	public void updateDipendente(String matricola, String nome, String cognome, Date data_di_nascita, String indirizzo,
			String citta, int id_ref_nazionalita, int id_dipendente) {

		dipendenteRepository.updateDipendente(matricola, nome, cognome, data_di_nascita, indirizzo, citta,
				id_ref_nazionalita, id_dipendente);
	}

	public void deleteByIdDip(int id_dipendente) {
		dipendenteRepository.deleteByIdDip(id_dipendente);
	}
	
	
}

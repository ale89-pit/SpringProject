package it.tecninf.hrmanagement.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.tecninf.hrmanagement.dto.DipendenteDto;
import it.tecninf.hrmanagement.dto.DipendenteDtoUpdate;
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
		
			if(!dipendenteRepository.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nessun Dipendente trovato");
			}
	
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
	
	
	public  Set<Tipskill> getSkillByDip (int idDip){
		Dipendente dip = getByID(idDip).get();
		System.out.println(dip);
			
			return  dip.getSkills();
		
	}
	

//	public void updateDipendente(String matricola, String nome, String cognome, Date data_di_nascita, String indirizzo,
//			String citta, int id_ref_nazionalita, int id_dipendente) {
//		
//		dipendenteRepository.updateDipendente(matricola, nome, cognome, data_di_nascita, indirizzo, citta,
//				id_ref_nazionalita, id_dipendente);
//	}
	
	public void updateDipendente(DipendenteDtoUpdate dip, int id_dipendente) {
		Dipendente d = getByID(id_dipendente).get();
		
		d.setCitta(dip.getCitta());
		d.setCognome(dip.getCognome());
		d.setDataDiNascita(dip.getDataDiNascita());
		d.setIndirizzo(dip.getIndirizzo());
		d.setNome(dip.getNome());
		dipendenteRepository.updateDipendente(d.getMatricola(),d.getNome() , d.getCognome(), d.getDataDiNascita(), d.getIndirizzo(), d.getCitta(),
				d.getRefNazionalita().getIdRefNazionalita(), id_dipendente);
	}

	public void deleteByIdDip(int id_dipendente) {
		dipendenteRepository.deleteById(id_dipendente);
	}
	
	
}

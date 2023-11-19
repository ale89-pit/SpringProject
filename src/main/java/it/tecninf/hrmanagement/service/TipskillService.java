package it.tecninf.hrmanagement.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tecninf.hrmanagement.model.Competenze;
import it.tecninf.hrmanagement.model.Curriculum;
import it.tecninf.hrmanagement.model.Dipendente;
import it.tecninf.hrmanagement.model.Tipskill;
import it.tecninf.hrmanagement.repository.CompetenzeRepository;
import it.tecninf.hrmanagement.repository.CurriculumRepository;
import it.tecninf.hrmanagement.repository.DipendenteRepository;
import it.tecninf.hrmanagement.repository.TipskillRepository;

@Service
public class TipskillService {
	
	@Autowired
	private TipskillRepository tipskillRepository;
	@Autowired
	private CompetenzeRepository compentenzeRepository;
	@Autowired
	private CurriculumRepository curriculumRepository;
	@Autowired
	private DipendenteRepository dipendenteRepository;
	@Autowired
	private DipendenteService dipendeteService;
	
	public List<Tipskill> listaSkill() {
		return (List<Tipskill>) tipskillRepository.listaSkill();
	}
	
	public void cancellaSkill(int id_tip_skill) {
		tipskillRepository.cancellaSkill(id_tip_skill);
	}
	
	public void aggiungiSkill(Tipskill tipskill) {
		tipskillRepository.save(tipskill);
	}
	//non dai la possibilit che ci sia uno storico di CVs
	public void aggiungiSkillToCV(int idCurriculum,List<Integer> tpskill) throws IOException {
	    Curriculum c = curriculumRepository.findById(idCurriculum).orElse(null);

	    if (c != null) {
	        Dipendente d = c.getDipendente();
	        Set<Tipskill> skill = dipendeteService.getSkillByDip(d.getIdDipendente());
	        
	        if (d != null) {
	            for (Integer s : tpskill) {
	                Tipskill t = tipskillRepository.findById(s).orElse(null);

	                if (t != null) {
	                    if (skill.contains(t)) {
	                        throw new IOException("Questa skill: " + t.getTipologiaSkill() + " è già presente");
	                    } else {
	                        Competenze competenza = new Competenze();
	                        competenza.setIdTipskill(t.getIdTipskill());
	                        competenza.setIdDipendente(d.getIdDipendente());
	                        competenza.setIdCurriculum(idCurriculum);
	                        System.out.println(competenza);
	                        compentenzeRepository.save(competenza);
	                    }
	                } else {
	                    throw new IOException("TipSkill non trovato per l'id " + s);
	                }
	            }
	        } else {
	            throw new IOException("Nessun dipendente trovato per il curriculum con ID " + idCurriculum);
	        }
	    } else {
	        throw new IOException("Nessun curriculum trovato per l'ID " + idCurriculum);
	    }	
	}    
	
}

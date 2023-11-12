package it.tecninf.hrmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tecninf.hrmanagement.model.Tipskill;
import it.tecninf.hrmanagement.repository.TipskillRepository;

@Service
public class TipskillService {
	
	@Autowired
	private TipskillRepository tipskillRepository;
	
	public List<Tipskill> listaSkill() {
		return (List<Tipskill>) tipskillRepository.listaSkill();
	}
	
	public void cancellaSkill(int id_tip_skill) {
		tipskillRepository.cancellaSkill(id_tip_skill);
	}
	
	public void aggiungiSkill(Tipskill tipskill) {
		tipskillRepository.save(tipskill);
	}
}

package it.tecninf.hrmanagement.rest;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.tecninf.hrmanagement.model.Tipskill;
import it.tecninf.hrmanagement.service.TipskillService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("tipskill")
public class TipskillController {

	@Autowired
	private TipskillService tipskillService;
		
	@GetMapping("/listaskill")
	public List<Tipskill> listaSkill() {

		return (List<Tipskill>) tipskillService.listaSkill();
	}	
	
	@DeleteMapping("/cancellaskill")
	public void cancellaSkill(@RequestParam int id_tipskill) {		
		tipskillService.cancellaSkill(id_tipskill);
	}
	
	@PostMapping("/aggiungiskill")
	public void aggiungiSkill(@RequestBody Tipskill tipskill) {
		tipskillService.aggiungiSkill(tipskill);
	}
	
	@Transactional
	@Modifying
	@PostMapping("/addSkillToDip")
	
	public void addSkillToDip(@RequestParam int idCv,@RequestParam List<Integer> tpskill) throws IOException {
		tipskillService.aggiungiSkillToCV(idCv, tpskill);
	}
}

package it.tecninf.hrmanagement.rest;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.tecninf.hrmanagement.dto.CurriculumDto;
import it.tecninf.hrmanagement.model.Curriculum;
import it.tecninf.hrmanagement.model.Dipendente;
import it.tecninf.hrmanagement.service.CurriculumService;
import it.tecninf.hrmanagement.service.DipendenteService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("curriculum")
public class CurriculumController {
	@Autowired
	CurriculumService curriculumService;
	
	@Autowired
	private DipendenteService dipendenteService;


	@GetMapping("/findall")
	public List<Curriculum> findall() {
		List<Curriculum> lista1 = curriculumService.findall();
		return lista1;
	}
	
	@GetMapping("/findWithCompetence")
	public List<CurriculumDto> getCvWithCompetence(){
		List<CurriculumDto> filterList = new ArrayList<CurriculumDto>();
		List<Curriculum> list = curriculumService.getCvWithCompetence();
		
		for(Curriculum d : list) {
			
			CurriculumDto cDto = new CurriculumDto();
			byte[] base = d.getCurriculum();
			String text = Base64.getEncoder().encodeToString(base);
			Base64.Decoder decoder = Base64.getDecoder();			
			String result  = new String(decoder.decode(text));
			
			d.setPdfText(result);
				cDto.getCurriculum().add(d);
			
			cDto.setNome(d.getDipendente().getNome());
			cDto.setCognome(d.getDipendente().getCognome());
			filterList.add(cDto);
		}
		return filterList;
	}
	
	@PostMapping("/addCV/{id_dipendente}")
	public void addCvDipendente(@PathVariable int id_dipendente,@RequestParam("cv") MultipartFile cv) throws Exception {
	curriculumService.addCurriculum(id_dipendente, cv);
		
	}
}
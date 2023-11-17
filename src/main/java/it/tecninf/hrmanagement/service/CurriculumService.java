package it.tecninf.hrmanagement.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.tecninf.hrmanagement.model.Curriculum;
import it.tecninf.hrmanagement.model.Dipendente;
import it.tecninf.hrmanagement.model.Tipskill;
import it.tecninf.hrmanagement.repository.CurriculumRepository;
import it.tecninf.hrmanagement.repository.DipendenteRepository;

@Service
public class CurriculumService {
	@Autowired
	private CurriculumRepository repository;
	
	@Autowired
	private DipendenteRepository dipRepository;
	

	
	public List<Curriculum> findall(){
		List<Curriculum> lista = (List<Curriculum>)repository.findAll();
		
		for(Curriculum c : lista) {
			byte[] base = c.getCurriculum();
			String text = Base64.getEncoder().encodeToString(base);
			Base64.Decoder decoder = Base64.getDecoder();			
			String result  = new String(decoder.decode(text));
			
			c.setPdfText(result);	
			
		}
		return lista;
	}
	
	public List<Curriculum> getCvWithCompetence(){
		return repository.getCvWhitCompetence();
	}

	
	public void addCurriculums(Set<Curriculum> curriculum) {
		repository.saveAll(curriculum);
	}
	
	public void addCurriculum(int idDipendente,MultipartFile c) throws Exception {
		Dipendente d = dipRepository.findById(idDipendente).get();
	
		
		try {
			if(d != null && c != null) {
				byte[] originalBytes = Base64.getEncoder().encode(c.getBytes());
				
				
				Curriculum saveCv = new Curriculum();
				saveCv.setCurriculum(originalBytes);
				saveCv.setDipendente(d);
				;
				d.getCurriculum().add(repository.save(saveCv));
				dipRepository.updateDipendente(d.getMatricola(),d.getNome(), d.getCognome(), d.getDataDiNascita(), d.getIndirizzo(), d.getCitta(), d.getRefNazionalita().getIdRefNazionalita(), idDipendente);
				
			}else {
				throw new IOException("Nessun utente trovato");
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.getCause());
		}
	
	}
	



}

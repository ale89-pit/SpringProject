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

import it.tecninf.hrmanagement.exception.RecourceAlreadyPresenteException;
import it.tecninf.hrmanagement.exception.ResourceNotFoundException;
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

	public List<Curriculum> findall() {
		List<Curriculum> lista = (List<Curriculum>) repository.findAll();

		for (Curriculum c : lista) {
			byte[] base = c.getCurriculum();
			String text = Base64.getEncoder().encodeToString(base);
			Base64.Decoder decoder = Base64.getDecoder();
			String result = new String(decoder.decode(text));

			c.setPdfText(result);

		}
		return lista;
	}

	public List<Curriculum> getCvWithCompetence(List<String> skill) {
		return repository.getCvWhitCompetence(skill);
	}

	public void addCurriculums(Set<Curriculum> curriculum) {
		repository.saveAll(curriculum);
	}

	public void addCurriculum(int idDipendente, Set<MultipartFile> c) throws Exception {

		try {
			Dipendente d = dipRepository.findById(idDipendente).get();
			if (d != null && c != null) {
				for (MultipartFile file : c) {

					byte[] originalBytes = Base64.getEncoder().encode(file.getBytes());

					Curriculum saveCv = new Curriculum();
					saveCv.setCurriculum(originalBytes);
					saveCv.setDipendente(d);
					System.out.println(d.getCurriculum().add(saveCv));

					// se il cv è gia presente lancia un'eccezione e non lo salva
					if (d.getCurriculum().add((saveCv))) {
						repository.save(saveCv);
//						dipRepository.updateDipendente(d.getMatricola(),d.getNome(), d.getCognome(), d.getDataDiNascita(), d.getIndirizzo(), d.getCitta(), d.getRefNazionalita().getIdRefNazionalita(), idDipendente);

					} else {
						throw new RecourceAlreadyPresenteException("il Cv è gia stato caricato ",
								file.getOriginalFilename());

					}

				}
			}

		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Nessun utente trovato", e.getMessage(), idDipendente);

		} catch (RecourceAlreadyPresenteException e) {
			throw e;
		}

	}

}

package it.tecninf.hrmanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.tecninf.hrmanagement.model.Dipendente;


public interface DipendenteRepository extends CrudRepository<Dipendente, Integer> {
	
	@Query(value = "SELECT last_insert_id();", nativeQuery = true)
	public int lastIdDipendente();

	@Query(value = "SELECT * FROM hrmanagement.dipendente WHERE row_exist=1;", nativeQuery = true)
	public List<Dipendente> findAllEmp();

	@Query(value = "SELECT * FROM hrmanagement.dipendente WHERE row_exist=0 ORDER BY cognome ASC", nativeQuery = true)
	public List<Dipendente> findAllOldEmp();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE hrmanagement.dipendente SET row_exist=0 WHERE id_dipendente=?",nativeQuery=true)
	public void deleteByIdDip(int id_dipendente);
	
	@Query(value = "SELECT * FROM hrmanagement.dipendente INNER JOIN hrmanagement.competenze ON dipendente.id_dipendente = competenze.id_dipendente INNER JOIN hrmanagement.curriculum ON curriculum.id_dipendente = competenze.id_dipendente INNER JOIN hrmanagement.tipskill ON competenze.id_tipskill = tipskill.id_tipskill WHERE tipskill.tipologia_skill = ?", nativeQuery = true)
	public List<Dipendente> getSkillFilter(String skill);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO hrmanagement.dipendente (matricola, nome, cognome, data_di_nascita, indirizzo, citta, id_ref_nazionalita,row_exist) VALUES (?,?,?,?,?,?,?,?)", nativeQuery = true)
	public void addDipendente(String matricola, String nome, String cognome, Date data_di_nascita,
			String indirizzo, String citta, int id_ref_nazionalita,int row_exist);

	@Modifying
	@Transactional
	@Query(value = "UPDATE hrmanagement.dipendente SET matricola=?, nome=?, cognome=?, data_di_nascita=?, indirizzo=?, citta=?, id_ref_nazionalita=? WHERE id_dipendente=?", nativeQuery = true)
	public void updateDipendente(String matricola, String nome, String cognome, Date data_di_nascita,
			String indirizzo, String citta, int id_ref_nazionalita, int id_dipendente);
}

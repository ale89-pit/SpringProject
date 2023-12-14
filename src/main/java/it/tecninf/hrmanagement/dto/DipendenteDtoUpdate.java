package it.tecninf.hrmanagement.dto;

import java.util.Date;

public class DipendenteDtoUpdate {

	private String citta;

	private String cognome;

	private Date dataDiNascita;

	private String indirizzo;
	
	private String nome;

	
	
	public DipendenteDtoUpdate() {
		super();
	}



	public DipendenteDtoUpdate(String citta, String cognome, Date dataDiNascita, String indirizzo, String nome) {
		super();
		this.citta = citta;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.indirizzo = indirizzo;
		this.nome = nome;
	}



	public String getCitta() {
		return citta;
	}



	public void setCitta(String citta) {
		this.citta = citta;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public Date getDataDiNascita() {
		return dataDiNascita;
	}



	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}



	public String getIndirizzo() {
		return indirizzo;
	}



	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

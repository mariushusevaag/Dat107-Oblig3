package no.hvl.dat107;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ansatt", schema = "oblig3")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattID;

	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private LocalDate ansettelse_start;
	private String stilling;
	private int maanedslonn;
	private String avdeling;


	public Ansatt() {
		
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelse_start, String stilling,
			int maanedslonn, String avdeling) {
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelse_start = ansettelse_start;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling = avdeling;
	}

	public int getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(int maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public LocalDate getAnsettelsesDato() {
		return ansettelse_start;
	}

	public void setAnsettelsesDato(LocalDate ansettelse_start) {
		this.ansettelse_start = ansettelse_start;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public int getAnsattID() {
		return ansattID;
	}

	public void setAnsattId(int ansattID) {
		this.ansattID = ansattID;
	}
	
	public void setAvdeling(String avdeling) {
		this.avdeling = avdeling;
	}
	
	public String getAvdeling() {
		return avdeling;
	}
	
	@Override
	public String toString() {
		return "Ansatt { ID : " + this.ansattID + 
		" | Navn : " + this.fornavn + " " + this.etternavn + 
		" | Stilling : " + this.stilling + " | Månedslønn : " + this.maanedslonn + " | Avdeling : " + this.avdeling + " }";
	}
}
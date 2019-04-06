package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Prosjekt", schema = "oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjekt_id;

	private String prosjekt_navn;
	private String prosjekt_beskrivelse;

	public Prosjekt() {
		
	}

	public Prosjekt(String prosjekt_navn, String prosjekt_beskrivelse) {
		this.prosjekt_navn = prosjekt_navn;
		this.prosjekt_beskrivelse = prosjekt_beskrivelse;
	}
	
	public int getProsjekt_id() {
		return prosjekt_id;
	}

	public void setProsjekt_id(int prosjekt_id) {
		this.prosjekt_id = prosjekt_id;
	}

	public String getProsjekt_navn() {
		return prosjekt_navn;
	}

	public void setProsjekt_navn(String prosjekt_navn) {
		this.prosjekt_navn = prosjekt_navn;
	}

	public String getProsjekt_beskrivelse() {
		return prosjekt_beskrivelse;
	}

	public void setProsjekt_beskrivelse(String prosjekt_beskrivelse) {
		this.prosjekt_beskrivelse = prosjekt_beskrivelse;
	}

	@Override
	public String toString() {
		return "Prosjekt { ID : " + this.prosjekt_id + 
		" | Navn : " + this.prosjekt_navn +  " | Beskrivelse : " + this.prosjekt_beskrivelse + " }";
	}
}

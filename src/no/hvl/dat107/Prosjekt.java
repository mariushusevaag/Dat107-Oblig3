package no.hvl.dat107;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Prosjekt", schema = "oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjekt_id;

	private String prosjekt_navn;
	private String prosjekt_beskrivelse;
	
	
	@OneToMany(
			mappedBy = "prosjekt",
			fetch = FetchType.EAGER,
			cascade = { CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
		private List<ProsjektAnsatte> pDListe;

	
	public String verboseInfo() {
		return String.format(
			"Prosjekt\nNavn: '%s',\nBeskrivelse: '%s',\nAnsatte:\n%s\n",
			prosjekt_navn,
			prosjekt_beskrivelse,
			pDListe.stream().map(ProsjektAnsatte::toString).collect(Collectors.joining("\n"))
		);
	}
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
	
	public List<ProsjektAnsatte> getpDListe() {
		return pDListe;
	}

	public void setpDListe(List<ProsjektAnsatte> pDListe) {
		this.pDListe = pDListe;
	}

	public void addListe(ProsjektAnsatte prosjektAnsatte) {
		pDListe.add(prosjektAnsatte);
	}
	
	public void leggTilAnsatt(Ansatt ansatt) {
		ProsjektEAO pEAO = new ProsjektEAO();

		pEAO.leggTilAnsatt(ansatt, this);
	}

	public void leggTilAnsatt(List<Ansatt> ansatte) {
		for(Ansatt ansatt : ansatte) {
			leggTilAnsatt(ansatt);
		}
	}
	

	@Override
	public String toString() {
		return "Prosjekt { ID : " + this.prosjekt_id + 
		" | Navn : " + this.prosjekt_navn +  " | Beskrivelse : " + this.prosjekt_beskrivelse + " }";
	}
}

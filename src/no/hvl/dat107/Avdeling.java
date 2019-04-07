package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Avdeling", schema = "oblig3")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdeling_id;

	private String avdeling_navn;
	private int sjef_id;
	
/*	@OneToMany(
			mappedBy = "avd",
			fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
		private List<Ansatt> avdListe;
*/
	public Avdeling() {
		
	}

	public Avdeling(String avdeling_navn, int sjef_id) {
		this.avdeling_navn = avdeling_navn;
		this.sjef_id = sjef_id;
	}

	public int getAvdeling_id() {
		return avdeling_id;
	}

	public void setAvdeling_id(int avdeling_id) {
		this.avdeling_id = avdeling_id;
	}

	public String getAvdeling_navn() {
		return avdeling_navn;
	}

	public void setAvdeling_navn(String avdeling_navn) {
		this.avdeling_navn = avdeling_navn;
	}

	public int getSjef_id() {
		return sjef_id;
	}

	public void setSjef_id(int sjef_id) {
		this.sjef_id = sjef_id;
	}


	@Override
	public String toString() {
		return "Avdeling { ID : " + this.avdeling_id + 
		" | Navn : " + this.avdeling_navn + " | Sjef : " + this.sjef_id + " }";
	}
}
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
	private int avdelingID;

	private String avdelingsnavn;
	private int sjef;

	public Avdeling() {
		
	}

	public Avdeling(String avdelingsnavn, int sjef) {
		this.avdelingsnavn = avdelingsnavn;
		this.sjef = sjef;
	}

	public int getAvdelingID() {
		return avdelingID;
	}

	public void setAvdelingID(int avdelingID) {
		this.avdelingID = avdelingID;
	}

	public String getAvdelingsnavn() {
		return avdelingsnavn;
	}

	public void setAvdelingsnavn(String avdelingsnavn) {
		this.avdelingsnavn = avdelingsnavn;
	}

	public int getSjef() {
		return sjef;
	}

	public void setSjef(int sjef) {
		this.sjef = sjef;
	}

	@Override
	public String toString() {
		return "Avdeling { ID : " + this.avdelingID + 
		" | Navn : " + this.avdelingsnavn + " | Sjef : " + this.sjef + " }";
	}
}
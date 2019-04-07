package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JobberMed", schema = "oblig3")
public class JobberMed {
	
	private int a_id; // ansatt_id
	private int p_id; // prosjekt_id
	private int timer; // timer jobbet på prosjektet
	
/*	@OneToMany(
			mappedBy = "avd",
			fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
		private List<Ansatt> avdListe;
*/
	public JobberMed() {
		
	}

	public JobberMed(int a_id, int p_id, int timer) {
		this.a_id = a_id;
		this.p_id = p_id;
		this.timer = timer;
	}
	
	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return "Ansatte som jobber i prosjekter { Ansatt : " + this.a_id + 
		" | Prosjekt : " + this.p_id + " | Timer : " + this.timer + " }";
	}
}

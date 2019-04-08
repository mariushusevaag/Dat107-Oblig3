package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProsjektAnsatte", schema = "oblig3")
@IdClass(ProsjektAnsatte.class)
public class ProsjektAnsatte {

	@Id
	@ManyToOne
	private int a_id;

	@Id
	@ManyToOne
	private int p_id;

	private String rolle;
	private Double timer;
	
	public ProsjektAnsatte() {

	}
	
	public ProsjektAnsatte(int a_id, int p_id, String rolle, Double timer) {
		this.a_id = a_id;
		this.p_id = p_id;
		this.rolle = rolle;
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

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public Double getTimer() {
		return timer;
	}

	public void setTimer(Double timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return "Prosjekter med ansatte { Prosjekt ID : " + this.p_id + 
				" | Ansatt ID : " + this.a_id + " | Rolle : " + this.rolle + 
				" | Timer : " + this.timer + " }";
	}
}

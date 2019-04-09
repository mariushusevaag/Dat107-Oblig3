package no.hvl.dat107;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProsjektAnsatte", schema = "oblig3")
@IdClass(ProsjektAnsattePK.class)
public class ProsjektAnsatte {

	@Id
	@ManyToOne
	@JoinColumn(name = "ansatt_id")
	private Ansatt ansatt;
	@Id
	@ManyToOne
	@JoinColumn(name = "prosjekt_id")
	private Prosjekt prosjekt;
	
	private String rolle;
	private BigDecimal timer;
	
	public ProsjektAnsatte() {

	}

	public ProsjektAnsatte(Ansatt ansatt, Prosjekt prosjekt) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;

		this.rolle = "Ikke satt";
		this.timer = BigDecimal.ZERO;
	}

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public BigDecimal getTimer() {
		return timer;
	}

	public void setTimer(BigDecimal timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, Timer: %s, Rolle: '%s'",
			prosjekt,
			ansatt,
			timer,
			rolle
		);
	}
}

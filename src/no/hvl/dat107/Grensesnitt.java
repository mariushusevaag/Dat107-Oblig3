package no.hvl.dat107;

import java.util.Scanner;

public class Grensesnitt {

	private AnsattEAO ansattEAO;
	private AvdelingEAO avdelingEAO;
	private ProsjektEAO prosjketEAO;
	private ProsjektAnsatteEAO prosjektAnsatteEAO;
	
	public Grensesnitt() {
		this.ansattEAO = new AnsattEAO();
		this.avdelingEAO = new AvdelingEAO();
		this.prosjketEAO = new ProsjektEAO();
		this.prosjektAnsatteEAO = new ProsjektAnsatteEAO();	
	}
	
	Scanner sc = new Scanner(System.in);
	
	public void OppstartsMeny() {
		System.out.println("Hva vil du undersøke?");
		System.out.println("1 - Ansatte.");
		System.out.println("2 - Avdelinger.");
		System.out.println("3 - Prosjekter");
		System.out.println();
		System.out.println("Skriv tall tilsvarende ditt valg.");
		System.out.println();
	}
	
	
	
	
}

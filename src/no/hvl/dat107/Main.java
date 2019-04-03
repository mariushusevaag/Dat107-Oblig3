package no.hvl.dat107;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean ferdig = false;

		while(!ferdig) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Meny:");
			System.out.println("0  = Avslutt2");
			System.out.println("1  = Søk etter ansatt på ID, eller brukernavn");
			System.out.println("2  = Skriv ut alle ansatte");
			System.out.println("3  = Oppdater en ansatt");
			System.out.println("4  = Legg til ansatt");

			switch(scanner.nextLine()) {

				case "0": {
					//Avslutt program
					ferdig = true;
					break;
				}
				case "1": {
					//Søk etter ansatt på ansatt Id
					System.out.println("Søk etter ansatt, du kan skrive brukernavn, eller id:");
					Ansatt funnet = finnAnsatt(scanner);
					if(funnet == null) {
						System.out.println("Fant ikke den ansatte");
					}
					System.out.println(funnet);
					break;
				}
				case "2": {
					//Skriv ut liste av ansatte
					AnsattEAO ansatt = new AnsattEAO();
					ansatt.skrivUtTabell();
					break;
				}
				case "3": {
					//Oppdater en ansatt, endre stilling, og eller lønn
					System.out.println("Skriv søkestreng: ID/Brukernavn på ansatt");
					AnsattEAO ansatt = new AnsattEAO();
					Ansatt funnet = finnAnsatt(scanner);
					if(funnet == null) {
						System.out.println("Fant ikke den ansatte");
						break;
					}
					System.out.println("1 = Endre lønn");
					System.out.println("2 = Endre stilling");
					System.out.println("3 = Endre lønn og stilling");
					switch(scanner.nextLine()) {
						case "1": {
							System.out.println("Skriv inn månedslønn:");
							funnet.setMaanedslonn(Integer.valueOf(scanner.nextLine()));
							ansatt.oppdaterAnsatt(funnet);
							break;
						}
						case "2": {
							System.out.println("Skriv inn stilling:");
							funnet.setStilling(scanner.nextLine());
							ansatt.oppdaterAnsatt(funnet);
							break;
						}
						case "3": {
							System.out.println("Skriv inn månedslønn:");
							funnet.setMaanedslonn(Integer.valueOf(scanner.nextLine()));
							System.out.println("Skriv inn stilling:");
							funnet.setStilling(scanner.nextLine());
							ansatt.oppdaterAnsatt(funnet);
							break;
						}
					}
					
					break;
				}
				case "4": {
					//Legge til ny ansatt, samt kunne spesifisere avdeling de skal jobbe i
					//String brukerNavn, String forNavn, String etterNavn, LocalDate ansettelseDato,
					//		String stilling, int maanedslonn, int avdelingNr
					System.out.println("Oppgi brukernavn, maks 4 bokstaver:");
					String brukernavn = scanner.nextLine();
					System.out.println("Oppgi fornavn:");
					String fornavn = scanner.nextLine();
					System.out.println("Oppgi etternavn:");
					String etternavn = scanner.nextLine();
					System.out.println("Skriv inn ansettelsesdato, ÅÅÅÅ-MM-DD:");
					LocalDate ansDato = LocalDate.parse(scanner.nextLine());
					System.out.println("Oppgi stilling:");
					String stilling = scanner.nextLine();
					System.out.println("Oppgi månedslønn:");
					int mndln = Integer.valueOf(scanner.nextLine());

				
					AnsattEAO ansatt = new AnsattEAO();
					ansatt.leggTilAnsatt(brukernavn, fornavn, etternavn, ansDato, stilling, mndln);
					ansatt.skrivUtTabell();
					break;
				}
			
			}
		}
	}


	public static Ansatt finnAnsatt(Scanner sc) {
		String rawid = sc.nextLine();
		AnsattEAO ansatt = new AnsattEAO();
		try {
			int iid = Integer.parseInt(rawid);
			return ansatt.finnAnsattMedID(iid);
		} catch(NumberFormatException e) {
			return ansatt.finnAnsattPaaNavn(rawid);
		}

	}
}


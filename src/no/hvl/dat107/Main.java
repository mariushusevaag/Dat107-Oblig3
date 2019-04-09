package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.util.Iterator;


public class Main {

	public static void main(String[] args) {
		boolean ferdig = false;

		while(!ferdig) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Meny:");
			System.out.println("0  = Avslutt");
			System.out.println("1  = Søk etter ansatt på ID, eller brukernavn");
			System.out.println("2  = Skriv ut alle ansatte");
			System.out.println("3  = Oppdater en ansatt");
			System.out.println("4  = Legg til ansatt");
			System.out.println("5  = Søk etter avdeling på ID");
			System.out.println("6  = Skriv ut alle ved avdeling");
			System.out.println("7  = Legg til avdeling");
			System.out.println("8  = Legg til prosjekt");
			System.out.println("9  = Legg til prosjektdeltager");
			

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
					System.out.println("4 = Endre avdeling");
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
						case "4": {
							if (!erSjef(funnet)) {
								System.out.println("Velg ny avdeling, ved hjelp av id:");
								int nyAvd = Integer.valueOf(scanner.nextLine());
								
								oppdaterAvdelingForAnsatt(funnet, nyAvd);
							} else {
								System.out.println("Kan ikke oppdatere avdeling fordi " + funnet.getBrukernavn() + " er sjef på en annen avdeling.");
							}
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
					System.out.println("Oppgi avdeling:");
					String avdeling = scanner.nextLine();

				
					AnsattEAO ansatt = new AnsattEAO();
					ansatt.leggTilAnsatt(brukernavn, fornavn, etternavn, ansDato, stilling, mndln, avdeling);
					ansatt.skrivUtTabell();
					break;
				}
				case "5": {
					//Søk etter avdeling på avdeling Id
					System.out.println("Søk etter avdeling med id:");
					Avdeling funnet = finnAvdeling(scanner);
					if(funnet == null) {
						System.out.println("Fant ikke den ansatte");
					}
					System.out.println(funnet);
					break;
				}
				case "6": {
					//Skriv ut alle på en avdeling
					System.out.println("Skriv inn avdeling id:");
					skrivAlleVedAvdeling(scanner);
					break;
				}
				case "7": {
					//Legge til en ny avdeling, samt angi sjef i ny avdeling
					System.out.println("Skriv søkestreng: ID/Brukernavn på sjef i den nye avdelingen");
					Ansatt funnet = finnAnsatt(scanner);
					if(funnet == null) {
						System.out.println("Fant ikke den ansatte");
						break;
					}
					
					if (!erSjef(funnet)) {
						System.out.println("Skriv inn navn på ny avdeling");
						String avdelingsNavn = scanner.nextLine();
						leggTilAvdeling(avdelingsNavn, funnet.getAnsatt_id());
						
					} else {
						System.out.println("Kan ikke oppdatere avdeling fordi " + funnet.getBrukernavn() + " er sjef på en annen avdeling.");
					}
					break;
				}
				case "8": {
					// Legge til nytt prosjekt
					System.out.println("Oppgi navn på prosjektet:");
					String prosjekt_navn = scanner.nextLine();
					System.out.println("Oppgi Beskrivelse:");
					String prosjekt_beskrivelse = scanner.nextLine();
					

				
					ProsjektEAO prosjekt = new ProsjektEAO();
					prosjekt.leggTilProsjekt(prosjekt_navn, prosjekt_beskrivelse);
					prosjekt.skrivUtProsjektTabell();
					break;
				}
				case "9": {
					System.out.println("Oppgi Prosjekt ID: ");
					int p_id = Integer.parseInt(scanner.nextLine());
					System.out.println("Oppgi Ansatt ID: ");
					int a_id = Integer.parseInt(scanner.nextLine());
					System.out.println("Oppgi ansattes rolle i prosjektet: ");
					String rolle = scanner.nextLine();
					System.out.println("Oppgi antall arbeidstimer: ");
					Double timer = Double.parseDouble(scanner.nextLine());
					
					ProsjektAnsatteEAO pa = new ProsjektAnsatteEAO();
					pa.leggTilDeltager(p_id, a_id, rolle, timer);
					
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
	
	public static Avdeling finnAvdeling(Scanner sc) {
		String input = sc.nextLine();
		AvdelingEAO avdeling = new AvdelingEAO();
		int id = Integer.parseInt(input);
		return avdeling.finnAvdelingMedID(id);
	}
	
	public static Avdeling finnAvdeling(int sc) {
		AvdelingEAO avdeling = new AvdelingEAO();
		return avdeling.finnAvdelingMedID(sc);
	}
	
	public static void skrivAlleVedAvdeling(Scanner sc) {
		AvdelingEAO avdeling = new AvdelingEAO();
		String avdelingNavn = finnAvdeling(sc).getAvdeling_navn();
		
		List<Ansatt> ansatte = avdeling.alleAnsatteVedAvdeling(avdelingNavn);
		Iterator<Ansatt> iter = ansatte.iterator();
		while (iter.hasNext()) {
			Ansatt ansatt = iter.next();
			if (erSjef(ansatt)) {
				System.out.println("*" + ansatt + "*");
			} else {
				System.out.println(ansatt);
			}
		}
	}
	private static boolean erSjef(Ansatt a) {
		boolean sjef = false;
		AvdelingEAO avdeling = new AvdelingEAO();
		Avdeling avd = avdeling.finnAvdelingPaaNavn(a.getAvdeling());

		if (avd.getSjef_id() == a.getAnsatt_id() && avd != null) {
			sjef = true;
		}

		return sjef;
	}
	
	public static void oppdaterAvdelingForAnsatt(Ansatt a, int nyAvdelingId) {
		if (!erSjef(a)) {
			
			String avdelingNavn = finnAvdeling(nyAvdelingId).getAvdeling_navn();
			
			a.setAvdeling(avdelingNavn);
			AvdelingEAO avdeling = new AvdelingEAO();
			avdeling.oppdaterAvdelingForAnsatt(a);
			System.out.println("Ansatt " + a.getAnsatt_id() + ", " + a.getBrukernavn() + " har fått ny avdeling: "
					+ avdeling.finnAvdelingMedID(nyAvdelingId));
		} else {
			System.out.println(
					"Kan ikke oppdatere avdeling fordi " + a.getBrukernavn() + " er sjef på en annen avdeling.");
		}
	}
	
	public static void leggTilAvdeling(String navn, int id) {
		try {
			AnsattEAO ansatt = new AnsattEAO();
			Ansatt a = ansatt.finnAnsattMedID(id);
			if (!erSjef(a) && a != null) {
				Avdeling avd = new Avdeling(navn, id);
				AvdelingEAO avdeling = new AvdelingEAO();
				avdeling.leggTilNyAvdeling(avd);
				
				a.setStilling("sjef");
				ansatt.oppdaterAnsatt(a);

				a.setAvdeling(avd.getAvdeling_navn());
				avdeling.oppdaterAvdelingForAnsatt(a);
				
				System.out.println("Ansatt " + a.getAnsatt_id() + ", " + a.getBrukernavn() + " er nå sjef i den nye avdelingen: "
						+ navn);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}


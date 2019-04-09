package no.hvl.dat107;

@SuppressWarnings("unused")
public class ProsjektAnsattePK {
	private int ansatt;
    
	private int prosjekt;
    
    public ProsjektAnsattePK() {}
    
    public ProsjektAnsattePK(int ansattId, int prosjektId) {
        this.ansatt = ansattId;
        this.prosjekt = prosjektId;
    }
}


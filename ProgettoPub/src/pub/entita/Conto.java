package pub.entita;

/**
 * @authors Giuseppe, Gillini
 * 
 * Utilizzata per stampare le ordinazioni fatte su uno scontrino.
 *
 */

public class Conto {	
	//Istanze
	private String nomeProdotto;
	private float costo;
	private int idTavolo;
	private float totale;
	
	//Costruttore
	public Conto(){
		
	}
	
	//Get & Set
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public int getIdTavolo() {
		return idTavolo;
	}
	
	public void setIdTavolo(int idTavolo) {
		this.idTavolo = idTavolo;
	}
	
	public float getTotale() {
		return totale;
	}
	
	public void setTotale(float totale) {
		this.totale = totale;
	}
	
}

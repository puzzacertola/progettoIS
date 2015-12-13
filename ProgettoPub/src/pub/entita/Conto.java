package pub.entita;

/**
 * @authors Giuseppe, Gillini
 * 
 * Utilizzata per stampare le ordinazioni fatte su uno scontrino.
 *
 */

public class Conto {	
	//Istanze
	private int quantita;
	private String nomeProdotto;
	private float costo;

	public Conto(int quantita, String nomeProdotto, float costo){
		this.quantita = quantita;
		this.nomeProdotto = nomeProdotto;
		this.costo = costo;	
	}

	public int getQuantita() {
		return quantita;
	}

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

}

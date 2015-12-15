package pub.entita;

/**
 * @authors Giuseppe, Gillini
 * 
 * Utilizzata per stampare le ordinazioni fatte su uno scontrino.
 *
 */

public class Conto {	
	//Istanze
	/**
	 * @uml.property  name="quantita"
	 */
	private int quantita;
	/**
	 * @uml.property  name="nomeProdotto"
	 */
	private String nomeProdotto;
	/**
	 * @uml.property  name="costo"
	 */
	private float costo;

	public Conto(int quantita, String nomeProdotto, float costo){
		this.quantita = quantita;
		this.nomeProdotto = nomeProdotto;
		this.costo = costo;	
	}

	/**
	 * @return
	 * @uml.property  name="quantita"
	 */
	public int getQuantita() {
		return quantita;
	}

	//Costruttore
	public Conto(){

	}

	//Get & Set
	/**
	 * @return
	 * @uml.property  name="nomeProdotto"
	 */
	public String getNomeProdotto() {
		return nomeProdotto;
	}

	/**
	 * @param nomeProdotto
	 * @uml.property  name="nomeProdotto"
	 */
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	/**
	 * @return
	 * @uml.property  name="costo"
	 */
	public float getCosto() {
		return costo;
	}

	/**
	 * @param costo
	 * @uml.property  name="costo"
	 */
	public void setCosto(float costo) {
		this.costo = costo;
	}

}

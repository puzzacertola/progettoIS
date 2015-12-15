package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Utilizzata per stampare sulla jList degli ordini della Gui di cameriere, le ordinazioni correnti.
 *
 */

public class Ordinazione {
	/**
	 * @uml.property  name="quantita"
	 */
	private int quantita;
	/**
	 * @uml.property  name="costo"
	 */
	private float costo;
	/**
	 * @uml.property  name="nome"
	 */
	private String nome;

	public Ordinazione(int quantita, float costo, String nome){
		this.quantita = quantita;
		this.costo = costo;
		this.nome = nome;
	}

	/**
	 * @return
	 * @uml.property  name="quantita"
	 */
	public int getQuantita() {
		return quantita;
	}

	/**
	 * @param quantita
	 * @uml.property  name="quantita"
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
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

	/**
	 * @return
	 * @uml.property  name="nome"
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 * @uml.property  name="nome"
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}

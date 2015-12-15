package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Utilizzata per tenere in memoria le ordinazioni prese ad un tavolo, per poi inserirle nella tabella
 * Ordini del database.
 *
 */

public class Prodotto {	
	//Istanze
	/**
	 * @uml.property  name="idProdotto"
	 */
	private int idProdotto;
	/**
	 * @uml.property  name="nome"
	 */
	private String nome;
	/**
	 * @uml.property  name="descrizione"
	 */
	private String descrizione;
	/**
	 * @uml.property  name="costo"
	 */
	private float costo;
	/**
	 * @uml.property  name="tipo"
	 */
	private String tipo;

	//Costruttore
	public Prodotto(int id, String nome, String descrizione, float costo, String tipo){
		this.idProdotto=id;
		this.nome=nome;
		this.descrizione=descrizione;
		this.costo=costo;
		this.tipo=tipo;
	}

	public Prodotto(int id, String nome, String descrizione, float costo){
		this.idProdotto=id;
		this.nome=nome;
		this.descrizione=descrizione;
		this.costo=costo;		
	}

	//Get & Set
	/**
	 * @return
	 * @uml.property  name="idProdotto"
	 */
	public int getIdProdotto() {
		return idProdotto;
	}

	/**
	 * @param idProdotto
	 * @uml.property  name="idProdotto"
	 */
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	@Override
	public boolean equals(Object o){
		Prodotto p = (Prodotto)o;
		return p.idProdotto == this.idProdotto;
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

	/**
	 * @return
	 * @uml.property  name="descrizione"
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 * @uml.property  name="descrizione"
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

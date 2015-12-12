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
	private int idProdotto;
	private String nome;
	private String descrizione;
	private float costo;
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
	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	@Override
	public boolean equals(Object o){
		Prodotto p = (Prodotto)o;
		return p.idProdotto == this.idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
}

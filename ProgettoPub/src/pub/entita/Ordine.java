package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Utilizzata per stampare sulla jList degli ordini della Gui di cuoco e barista, le ordinazioni da fare,
 * e sulla Gui del cassiere le ordinazioni fatte.
 *
 */

public class Ordine {
	/**
	 * @uml.property  name="idOrdine"
	 */
	private int idOrdine;
	/**
	 * @uml.property  name="idProdotto"
	 */
	private int idProdotto;
	/**
	 * @uml.property  name="tavolo"
	 */
	private int tavolo;
	/**
	 * @uml.property  name="idCameriere"
	 */
	private int idCameriere;
	/**
	 * @uml.property  name="stato"
	 */
	private String stato;
	/**
	 * @uml.property  name="nomeProdotto"
	 */
	private String nomeProdotto;
	/**
	 * @uml.property  name="quantita"
	 */
	private int quantita;

	public Ordine(int idProdotto, int tavolo, int idCameriere, String stato){
		this.idProdotto = idProdotto;
		this.tavolo = tavolo;
		this.idCameriere = idCameriere;
		this.stato = stato;		
	}

	public Ordine(int idOrdine, int idProdotto, int tavolo, int idCameriere, String stato){
		this.setIdOrdine(idOrdine);
		this.idProdotto = idProdotto;
		this.tavolo = tavolo;
		this.idCameriere = idCameriere;
		this.stato = stato;		
	}

	public Ordine(int idOrdine, String nomeProdotto, int tavolo, String stato){
		this.setIdOrdine(idOrdine);
		this.setNomeProdotto(nomeProdotto);
		this.tavolo = tavolo;
		this.stato = stato;		
	}

	public Ordine(int quantita, String nomeProdotto, int tavolo, int idCameriere){
		this.quantita = quantita;
		this.setNomeProdotto(nomeProdotto);
		this.tavolo = tavolo;
		this.idCameriere = idCameriere;	
	}

	/**
	 * @return
	 * @uml.property  name="quantita"
	 */
	public int getQuantita() {
		return quantita;
	}

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

	/**
	 * @return
	 * @uml.property  name="tavolo"
	 */
	public int getTavolo() {
		return tavolo;
	}

	/**
	 * @param tavolo
	 * @uml.property  name="tavolo"
	 */
	public void setTavolo(int tavolo) {
		this.tavolo = tavolo;
	}

	/**
	 * @return
	 * @uml.property  name="idCameriere"
	 */
	public int getIdCameriere() {
		return idCameriere;
	}

	/**
	 * @param idCameriere
	 * @uml.property  name="idCameriere"
	 */
	public void setIdCameriere(int idCameriere) {
		this.idCameriere = idCameriere;
	}

	/**
	 * @return
	 * @uml.property  name="stato"
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato
	 * @uml.property  name="stato"
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return
	 * @uml.property  name="idOrdine"
	 */
	public int getIdOrdine() {
		return idOrdine;
	}

	/**
	 * @param idOrdine
	 * @uml.property  name="idOrdine"
	 */
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

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

}

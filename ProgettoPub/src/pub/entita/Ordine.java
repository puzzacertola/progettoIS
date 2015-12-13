package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Utilizzata per stampare sulla jList degli ordini della Gui di cuoco e barista, le ordinazioni da fare,
 * e sulla Gui del cassiere le ordinazioni fatte.
 *
 */

public class Ordine {
	private int idOrdine;
	private int idProdotto;
	private int tavolo;
	private int idCameriere;
	private String stato;
	private String nomeProdotto;
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

	public int getQuantita() {
		return quantita;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getTavolo() {
		return tavolo;
	}

	public void setTavolo(int tavolo) {
		this.tavolo = tavolo;
	}

	public int getIdCameriere() {
		return idCameriere;
	}

	public void setIdCameriere(int idCameriere) {
		this.idCameriere = idCameriere;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

}

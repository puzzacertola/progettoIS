package pub.entita;

public class Ordine {
	private int idOrdine;
	private int idProdotto;
	private int tavolo;
	private int idCameriere;
	private String stato;
	
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
	
}

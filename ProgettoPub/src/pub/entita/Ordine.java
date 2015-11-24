package pub.entita;

public class Ordine {
	
	//Istanze
	private int idOrdine;
	private int idProdotto;
	private int idTavolo;
	private int idCameriere;
	private String Stato;
	
	//Costruttore 	
	public Ordine(){
		
	}
	
	//Get & Set
	public int getIdOrdine() {
		return idOrdine;
	}	
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	public int getIdTavolo() {
		return idTavolo;
	}
	
	public void setIdTavolo(int idTavolo) {
		this.idTavolo = idTavolo;
	}
	
	public int getIdCameriere() {
		return idCameriere;
	}
	
	public void setIdCameriere(int idCameriere) {
		this.idCameriere = idCameriere;
	}
	
	public String getStato() {
		return Stato;
	}
	
	public void setStato(String stato) {
		Stato = stato;
	}
	
	//Metodi
	
}

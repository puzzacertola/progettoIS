package pub.dao;

import java.util.List;

import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Snack;

public interface DAOcameriere {
	
	public List<Bevanda> mostraBevande();
	public List<Snack> mostraSnack();
	public void inserisciOrdini(Ordine o);
	public List<Ordine> mostraOrdini();
	public void modificaOrdine(int idOrdine, int idProdotto);
	public void eliminaOrdine (int idOrdine);
	public List<Ordine> mostraOrdiniCameriere(int idCameriere);
	public List<Ordine>	mostraOrdiniTavolo(int tavolo);

}

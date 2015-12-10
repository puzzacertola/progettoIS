package pub.dao;

import java.util.List;

import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Snack;

/**
 * @authors Giuseppe, Giovanni
 *
 * Interfaccia DAO di Cameriere.
 * Contiene i metodi utilizzati da DAOCameriereImpl, dove vengono
 * create le query da mandare al database per far stampare a video i prodotti disponibili
 * nel menu del giorno e gli ordini fatti da un cameriere o presi ad un tavolo,
 * e inserire/modificare/eliminare un ordine.
 *
 */

public interface DAOCameriere {	
	public List<Bevanda> mostraBevande();
	public List<Snack> mostraSnack();
	public void inserisciOrdini(Ordine o);
	public List<Ordine> mostraOrdini();
	public void modificaOrdine(int idOrdine, int idProdotto);
	public void eliminaOrdine (int idOrdine);
	public List<Ordine> mostraOrdiniCameriere(int idCameriere);
	public List<Ordine>	mostraOrdiniTavolo(int tavolo);
}

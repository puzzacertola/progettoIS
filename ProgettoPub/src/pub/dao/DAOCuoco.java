package pub.dao;

import java.util.List;
import pub.entita.Ordine;

/**
 * @authors Giuseppe, Giovanni
 *
 * Interfaccia DAO di Cuoco.
 * Contiene i metodi utilizzati da DAOCuocoImpl, dove vengono
 * create le query da mandare al database per far stampare a video gli Snack ordinati, 
 * e per far settare "Pronto" agli ordini preparati.
 *
 */

public interface DAOCuoco {
	public List<Ordine> mostraOrdini();
	public void setPronto(String nome, String stato, int tavolo);
}

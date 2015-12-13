package pub.dao;

import java.util.List;
import pub.entita.Ordine;

/**
 * @authors Giuseppe, Giovanni
 *
 * Interfaccia DAO di Barista.
 * Contiene i metodi utilizzati da DAOBaristaImpl, dove vengono
 * create le query da mandare al database per far stampare a video le Bevande ordinate, 
 * e per far settare "Pronto" agli ordini preparati.
 *
 */

public interface DAOBarista {
	public List<Ordine> mostraOrdini();
	public void setPronto(String nome, String stato, int tavolo);
}

package pub.dao;

import java.util.List;
import pub.entita.Conto;

/**
 * @authors Giuseppe, Giovanni
 *
 * Interfaccia DAO di Cassiere.
 * Contiene i metodi utilizzati da DAOCassiereImpl, dove vengono
 * create le query da mandare al database per far stampare a video il conto di un tavolo.
 * Una volta stampato lo scontrino, le odinazioni pagate vengono cancellate dal database.
 *
 */

public interface DAOCassiere {
	public List<Conto> mostraOrdiniDaPagare(int tavolo);
	public String ottieniTotale(int tavolo);
	public void contoPagato(int numeroTavolo);
}

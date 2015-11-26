package pub.dao;

import java.util.List;

import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Prodotti;

public interface DAOcameriere {
	
	public List<Bevanda> mostraBevande();
	public List<Prodotti> mostraSnack();
	public void inserisciOrdini(Prodotti p);
	public List<Ordine> mostraOrdini();
	public void modificaOrdine(int o, int p);
	public void eliminaOrdine (int o);
	

}

package pub.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pub.entita.Conto;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Implementa l'interfaccia DAOCassiere.
 * Contiene quindi l'override dei metodi definiti nell'interfaccia.
 * 
 */

public class DAOCassiereImpl implements DAOCassiere {

	private DAOCassiereImpl() {}

	private static DAOCassiereImpl dao = null;

	public static DAOCassiereImpl getInstance(){
		if (dao == null){
			dao = new DAOCassiereImpl();
		}
		return dao;
	}

	/*
	 * getListaOrdini restituisce un ArrayList di Conto.
	 * Ogni Conto è costituito dai valori ottenuti dal ResultSet della query eseguita.  
	 */

	private ArrayList<Conto> getListaOrdini(ResultSet rs){
		ArrayList<Conto> listaOrdini = new ArrayList<Conto>();

		try {
			while(rs.next()){
				listaOrdini.add(new Conto(rs.getInt("Quantita"),
						rs.getString("Nome"), 
						rs.getFloat("Costo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella stringa di risposta");
		}
		return listaOrdini;		
	}

	/*
	 * mostraOrdiniDaPagare fa una select sulla tabella Ordini e Menu. Mostra il nome e la quntità di un prodotto ordinato, 
	 * ed il prezzo unitario del prodotto.
	 */

	@Override
	public List<Conto> mostraOrdiniDaPagare(int tavolo) {
		ArrayList<Conto> listaOrdini = new ArrayList<Conto>();
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select count(o.idOrdine) 'Quantita' , m.Nome, m.Costo "
					+ "from Ordini o, Menu m "
					+ "where o.idProdotto = m.IdProdotto and o.Stato = 'Consegnato' and o.Tavolo = " + tavolo
					+ " group by m.Nome ";

			ResultSet rs = stat.executeQuery(query);

			listaOrdini = this.getListaOrdini(rs);

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return listaOrdini;		
	}

	/*
	 * ottieniTotale fa una select sulla tabella Ordini e Menu. mostra il totale che il (tavolo) deve pagare
	 */

	@Override
	public String ottieniTotale(int tavolo) {
		String totale = "";
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select sum(m.Costo) 'Totale' "
					+ "from Ordini o, Menu m "
					+ "where o.idProdotto = m.IdProdotto and o.Stato = 'Consegnato' and o.Tavolo = " + tavolo;

			ResultSet rs = stat.executeQuery(query);

			rs.next();

			totale = rs.getString("Totale");

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return totale;		
	}

	@Override
	public void contoPagato(int numeroTavolo) {
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "delete from Ordini where Tavolo = " + numeroTavolo;

			stat.executeUpdate(query);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			System.out.println("Errore nella richiesta al DB");
		}
		
	}

}

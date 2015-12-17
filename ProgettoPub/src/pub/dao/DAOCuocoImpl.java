package pub.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pub.entita.Ordine;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Implementa l'interfaccia DAOCuoco.
 * Contiene quindi l'override dei metodi definiti nell'interfaccia.
 * 
 */

public class DAOCuocoImpl implements DAOCuoco {
	private DAOCuocoImpl(){}

	private static DAOCuocoImpl dao = null;

	public static DAOCuocoImpl getInstance(){
		if (dao == null){
			dao = new DAOCuocoImpl();
		}
		return dao;
	}

	/*
	 * getListaOrdini restituisce un ArrayList di Ordine.
	 * Ogni Ordine è costituito dai valori ottenuti dal ResultSet della query eseguita.  
	 */

	private ArrayList<Ordine> getListaOrdini(ResultSet rs){
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();

		try {
			while(rs.next()){
				listaOrdini.add(new Ordine(rs.getInt("Quantita"),
						rs.getString("Nome"), 
						rs.getInt("Tavolo"),
						rs.getInt("idCameriere")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella stringa di risposta");
		}
		return listaOrdini;		
	}

	/*
	 * mostraOrdini fa una select sulla tabella Ordini e Menu mostrando un prodotto ordinato e la quantità, 
	 * il tavolo dove è stata fatta l'ordinazione e il cameriere che l'ha eseguita.
	 */

	@Override
	public List<Ordine> mostraOrdini() {
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select count(o.idOrdine) 'Quantita' , m.Nome, o.Tavolo, o.idCameriere "
					+ "from Ordini o, Menu m "
					+ "where o.idProdotto = m.IdProdotto and m.Tipo like 'S%' and o.Stato = 'Da Fare' "
					+ "group by o.Tavolo, m.Nome, o.idCameriere "
					+ "order by o.idOrdine";

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
	 * setPronto fa una update sulla tabella Ordini settando il nuovo stato all'Ordine da modificare.
	 */

	@Override
	public void setPronto(String nome, String stato, int tavolo) {
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "update Ordini o join Menu m on o.idProdotto = m.IdProdotto " 
					+ "set o.Stato = '" + stato
					+ "' where m.Nome = '" + nome + "' and o.Tavolo = " + tavolo + " and m.Tipo like 'S%'";

			stat.executeUpdate(query);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			JOptionPane.showMessageDialog(new JFrame(), "Errore" 
					+ "Ordinazione non trovata", "Errore", JOptionPane.ERROR_MESSAGE);
			System.out.println("Errore nella richiesta al DB");
		}
	}

}

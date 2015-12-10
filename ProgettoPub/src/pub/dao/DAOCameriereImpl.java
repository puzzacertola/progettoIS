package pub.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Snack;


public class DAOCameriereImpl implements DAOCameriere {
	public static final int LIMITE = 22;

	private DAOCameriereImpl(){}

	private static DAOCameriereImpl dao = null;

	public static DAOCameriereImpl getInstance(){
		if (dao == null){
			dao = new DAOCameriereImpl();
		}
		return dao;
	}

	private ArrayList<Bevanda> getListaBevande(ResultSet rs){
		ArrayList<Bevanda> listaBevande = new ArrayList<Bevanda>();

		try {
			while(rs.next()){
				listaBevande.add(new Bevanda(rs.getInt("IdProdotto"), 
						rs.getString("Nome"),
						rs.getString("Descrizione"),
						rs.getFloat("Costo"),
						rs.getString("Tipo")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella stringa di risposta");
		}
		return listaBevande;		
	}

	private ArrayList<Snack> getListaSnack(ResultSet rs){
		ArrayList<Snack> listaSnack = new ArrayList<Snack>();

		try {
			while(rs.next()){
				listaSnack.add(new Snack(rs.getInt("IdProdotto"), 
						rs.getString("Nome"),
						rs.getString("Descrizione"),
						rs.getFloat("Costo"),
						rs.getString("Tipo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella stringa di risposta");
		}
		return listaSnack;		
	}

	private ArrayList<Ordine> getListaOrdini(ResultSet rs){
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();

		try {
			while(rs.next()){
				listaOrdini.add(new Ordine(rs.getInt("idOrdine"),
						rs.getInt("idProdotto"), 
						rs.getInt("Tavolo"),
						rs.getInt("idCameriere"),
						rs.getString("Stato")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nella stringa di risposta");
		}
		return listaOrdini;		
	}

	@Override
	public List<Bevanda> mostraBevande() {
		Calendar cal = Calendar.getInstance();

		String query = "";
		ArrayList<Bevanda> listaBevande = new ArrayList<Bevanda>();
		try {
			Statement stat = DAOSetting.getStatement();
			if((LIMITE - cal.get(Calendar.HOUR_OF_DAY)) > 0)
				query = "select * from Menu where Tipo like 'B%'";
			else
				query = "select * from Menu where Tipo like 'B%' and Tipo != 'BA'";

			ResultSet rs = stat.executeQuery(query);

			listaBevande = this.getListaBevande(rs);

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return listaBevande;
	}

	@Override
	public List<Snack> mostraSnack() {
		ArrayList<Snack> listaSnack = new ArrayList<Snack>();
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "select * from Menu where Tipo like 'S%'";

			ResultSet rs = stat.executeQuery(query);

			listaSnack = this.getListaSnack(rs);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return listaSnack;
	}

	@Override
	public void inserisciOrdini(Ordine o) {
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "insert into Ordini(idProdotto, Tavolo, idCameriere, Stato) values (" 
					+ o.getIdProdotto() + "," + o.getTavolo() + "," + o.getIdCameriere() 
					+ ",'" + o.getStato() + "')";

			stat.executeUpdate(query);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}

	}

	@Override
	public List<Ordine> mostraOrdini() {
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select * from Ordini";

			ResultSet rs = stat.executeQuery(query);

			listaOrdini = this.getListaOrdini(rs);

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return listaOrdini;
	}

	@Override
	public void modificaOrdine(int idOrdine, int idProdotto) {
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "update Ordini set idProdotto = " + idProdotto + 
					" where idOrdine = " + idOrdine;

			stat.executeUpdate(query);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
	}

	@Override
	public void eliminaOrdine(int idOrdine) {
		try {
			Statement stat = DAOSetting.getStatement();

			String query = "delete from Ordini where idOrdine = " + idOrdine;

			stat.executeUpdate(query);

			DAOSetting.closeStatement(stat);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}

	}

	@Override
	public List<Ordine> mostraOrdiniCameriere(int idCameriere) {
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select * from Ordini where idCameriere = " + idCameriere;

			ResultSet rs = stat.executeQuery(query);

			listaOrdini = this.getListaOrdini(rs);

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella richiesta al DB");
		}
		return listaOrdini;
	}

	@Override
	public List<Ordine> mostraOrdiniTavolo(int tavolo) {
		ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>();
		try{
			Statement stat = DAOSetting.getStatement();
			String query = "select * from Ordini where Tavolo = " + tavolo;

			ResultSet rs = stat.executeQuery(query);

			listaOrdini = this.getListaOrdini(rs);

			DAOSetting.closeStatement(stat);

		} catch (SQLException e){
			System.out.println("Errore nella richiesta al DB");
			e.printStackTrace();
		}
		return listaOrdini;

	}

}

package pub.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Prodotti;

public class DAOcameriereImpl implements DAOcameriere {


	@Override
	public List<Bevanda> mostraBevande() {
		String tipo = "B";
		ArrayList<Bevanda> listabevande= new ArrayList<Bevanda>();
		try {
			Statement stat = DAOSetting.getStatement();
			String query = "select * from Menu where Tipo like '" + tipo +"%'";
			
			ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                    listabevande.add(new Bevanda(rs.getString("nome"), 
                                    rs.getString("cognome"), 
                                    rs.getString("telefono"),
                                    rs.getString("email")));
            }
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listabevande;
	}

	@Override
	public List<Prodotti> mostraSnack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserisciOrdini(Prodotti p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ordine> mostraOrdini() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificaOrdine(int o, int p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminaOrdine(int o) {
		// TODO Auto-generated method stub

	}

}

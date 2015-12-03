package pub.entita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import pub.server.Server;

public class MyModelOrdini extends AbstractTableModel{
	
	String lista = "";
	Vector<Prodotti> prodotto;
	
	//costruttore
	public MyModelOrdini(){
		this.prodotto = new Vector<Prodotti>();
		
	}
	//aggiungi prodotto
	
	public String addProdotti(Prodotti prodotto){
		
		lista = prodotto.getNome() + " " + prodotto.getCosto();
		
		this.prodotto.add(prodotto);
		
		return lista;
	}
	
	public Vector<Prodotti> getProdotto() {
		return prodotto;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
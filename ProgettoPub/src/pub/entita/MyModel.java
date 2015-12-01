package pub.entita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import pub.server.Server;

public class MyModel extends AbstractTableModel{
	
	Vector<Bevanda> bevanda;
	String[] headers;
	//costruttore
	public MyModel(){
		
		bevanda = new Vector<Bevanda>();
		headers = new String[]{"IdProdotto", "Nome", "Descrizione", "Costo"};

	}
	//numero di righe
	public int contaRighe(){
	    return this.bevanda.size();
	 
	}
	//numero colonne
	public int contaColonne(){
		return headers.length;
	
	}
	//nome colonna
	public String nomeColonna(int i){
		return headers[i];
	}
	//ottieni valore cella
	public String getValueAt(int rowIndex, int columnIndex){
		
		return null;
	}

	//aggiungi prodotto
	public void addProdotti(String risposta){
		
		String[] arrayRiga= risposta.split("\\\n",-1);
		
		for(int i=0; i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Bevanda b=new Bevanda(Integer.parseInt(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],Float.parseFloat(arrayRisposta[3]));
			bevanda.add(b);

		}	
	}
	
	public String[] creaLista(){
		String lista = "";
		String[] listaArray;
		
		for (Bevanda b: this.bevanda)
			lista += b.getNome() + " " + b.getCosto() + ";";

		listaArray = lista.split("\\;",-1);
		
		return listaArray;
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
}

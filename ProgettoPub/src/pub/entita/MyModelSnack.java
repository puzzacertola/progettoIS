package pub.entita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import pub.server.Server;

public class MyModelSnack extends AbstractTableModel{
	
	Vector<Snack> snack;
	String[] headers;
	//costruttore
	public MyModelSnack(){
		
		snack = new Vector<Snack>();
		headers = new String[]{"IdProdotto", "Nome", "Descrizione", "Costo"};

	}
	//numero di righe
	public int contaRighe(){
	    return this.snack.size();
	 
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
			Snack s = new Snack(Integer.parseInt(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],Float.parseFloat(arrayRisposta[3]));
			snack.add(s);

		}	
	}
	
	public String[] creaLista(){
		String lista = "";
		String[] listaArray;
		
		for (Snack s: this.snack)
			lista += s.getNome() + " " + s.getCosto() + ";";

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
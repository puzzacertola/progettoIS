package pub.entita;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public abstract class MyModel extends AbstractTableModel{
	
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
		
		String[] arrayRiga= risposta.split("\n",-1);
		
		for(int i=0; i<arrayRiga.length;i++){
			String[] arrayRisposta= arrayRiga[i].split(" ",-1);
			Bevanda b=new Bevanda(Integer.getInteger(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],Float.valueOf(arrayRisposta[3]));
			bevanda.add(b);
			
		}
		
		
	}
	public static void main(String[] arg0){
		Bevanda
		
	}
}

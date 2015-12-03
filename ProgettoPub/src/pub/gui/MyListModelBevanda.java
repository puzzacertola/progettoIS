package pub.gui;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import pub.entita.Bevanda;

public class MyListModelBevanda implements ListModel<String> {

	private ArrayList<Bevanda> bevande = null;
	
	public MyListModelBevanda(String risposta){

		setData(risposta);
		
	}
	
	
	public ArrayList<Bevanda> getBevande() {
		return bevande;
	}


	public void setBevande(ArrayList<Bevanda> bevande) {
		this.bevande = bevande;
	}


	public void setData(String risposta){
		// Split della stringa e aggiunto a lista

		bevande = new ArrayList<Bevanda>();
		
		String[] arrayRiga= risposta.split("\\\n",-1);
		
		for(int i=0; i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Bevanda b=new Bevanda(Integer.parseInt(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],Float.parseFloat(arrayRisposta[3]));
			bevande.add(b);
		}			
		
	}
	
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int index) {
		
		return bevande.get(index).getNome() + " " + bevande.get(index).getCosto();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return bevande.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

}

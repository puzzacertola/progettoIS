package pub.gui;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import pub.entita.Snack;

public class MyListModelSnack implements ListModel<String> {

	private ArrayList<Snack> snack = null;

	public MyListModelSnack(String risposta){

		setData(risposta);

	}

	public ArrayList<Snack> getSnack() {
		return snack;
	}


	public void setBevande(ArrayList<Snack> snack) {
		this.snack = snack;
	}


	public void setData(String risposta){
		snack = new ArrayList<Snack>();

		String[] arrayRiga= risposta.split("\\\n",-1);

		for(int i=0; i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Snack s = new Snack(Integer.parseInt(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],Float.parseFloat(arrayRisposta[3]));
			snack.add(s);
		}			
	}


	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getElementAt(int index) {
		return snack.get(index).getNome() + " " + snack.get(index).getCosto();
	}

	@Override
	public int getSize() {
		return snack.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

}

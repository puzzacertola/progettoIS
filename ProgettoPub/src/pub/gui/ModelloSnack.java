package pub.gui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import pub.entita.Snack;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Modello utilizzato dalla JList degli Snack.
 *
 */

public class ModelloSnack extends AbstractListModel {

	/**
	 * @uml.property  name="snack"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="pub.entita.Snack"
	 */
	private ArrayList<Snack> snack = null;

	public ModelloSnack(String risposta){
		setData(risposta);
	}

	public ArrayList<Snack> getSnack() {
		return snack;
	}


	public void setSnack(ArrayList<Snack> snack) {
		this.snack = snack;
	}

	/*
	 * setData splitta la stringa di risposta ottenuta dal database, e ne salva il contenuto
	 * in un oggetto di tipo Snack, che sarà poi inserito nell'ArrayList snack.
	 * 
	 */

	public void setData(String risposta){
		this.snack = new ArrayList<Snack>();

		String[] arrayRiga = risposta.split("\\\n",-1);

		for(int i=0;i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Snack s = new Snack(Integer.parseInt(arrayRisposta[0]), arrayRisposta[1], arrayRisposta[2],
					Float.parseFloat(arrayRisposta[3]));
			this.snack.add(s);
		}			
	}

	//getElementAt è il metodo utilizzato dalla JList per stampare le stringhe in un JScrollPane

	@Override
	public String getElementAt(int index) {
		return snack.get(index).getNome() + " " + snack.get(index).getCosto();
	}

	//getSize ottiene la dimensione dell'ArrayList snack.

	@Override
	public int getSize() {
		return snack.size();
	}

}

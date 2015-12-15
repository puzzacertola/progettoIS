package pub.gui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import pub.entita.Bevanda;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Modello utilizzato dalla JList delle bevande.
 *
 */

public class ModelloBevande extends AbstractListModel {

	/**
	 * @uml.property  name="bevande"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="pub.entita.Bevanda"
	 */
	private ArrayList<Bevanda> bevande = null;

	public ModelloBevande(String risposta){
		setData(risposta);
	}

	public ArrayList<Bevanda> getBevande() {
		return bevande;
	}

	public void setBevande(ArrayList<Bevanda> bevande) {
		this.bevande = bevande;
	}

	/*
	 * setData splitta la stringa di risposta ottenuta dal database, e ne salva il contenuto
	 * in un oggetto di tipo Bevanda, che sarà poi inserito nell'ArrayList bevande.
	 * 
	 */

	public void setData(String risposta){
		bevande = new ArrayList<Bevanda>();

		String[] arrayRiga= risposta.split("\\\n",-1);

		for(int i=0; i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Bevanda b = new Bevanda(Integer.parseInt(arrayRisposta[0]),arrayRisposta[1],arrayRisposta[2],
					Float.parseFloat(arrayRisposta[3]));
			bevande.add(b);
		}			

	}

	//getElementAt è il metodo utilizzato dalla JList per stampare le stringhe in un JScrollPane

	@Override
	public String getElementAt(int index) {
		return bevande.get(index).getNome() + " " + bevande.get(index).getCosto();
	}

	//getSize ottiene la dimensione dell'ArrayList bevande.

	@Override
	public int getSize() {
		return bevande.size();
	}

}

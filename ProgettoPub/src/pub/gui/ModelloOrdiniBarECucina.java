package pub.gui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import pub.entita.Ordine;

public class ModelloOrdiniBarECucina extends AbstractListModel{
	/**
	 * @uml.property  name="ordini"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="pub.entita.Ordine"
	 */
	private ArrayList<Ordine> ordini = null;

	public ModelloOrdiniBarECucina(String risposta){
		setData(risposta);
	}

	public ArrayList<Ordine> getOrdini() {
		return ordini;
	}

	/*
	 * setData splitta la stringa di risposta ottenuta dal database, e ne salva il contenuto
	 * in un oggetto di tipo Ordine, che sarà poi inserito nell'ArrayList ordini.
	 * 
	 */

	public void setData(String risposta){
		this.ordini = new ArrayList<Ordine>();

		String[] arrayRiga = risposta.split("\\\n",-1);

		for(int i=0;i<arrayRiga.length-1;i++){ //-1 perchè ogni stringa termina con \n => l'ultima è vuota
			String[] arrayRisposta = arrayRiga[i].split("\\;",-1);
			Ordine o = new Ordine(Integer.parseInt(arrayRisposta[0]), arrayRisposta[1],
					Integer.parseInt(arrayRisposta[2]), Integer.parseInt(arrayRisposta[3]));
			this.ordini.add(o);
		}			
	}

	//getElementAt è il metodo utilizzato dalla JList per stampare le stringhe in un JScrollPane

	@Override
	public String getElementAt(int index) {
		return this.ordini.get(index).getQuantita() + "x " + this.ordini.get(index).getNomeProdotto() 
				+ " | Tavolo: " + this.ordini.get(index).getTavolo() + " | idCameriere: "
				+ this.ordini.get(index).getIdCameriere();
	}

	//getSize ottiene la dimensione dell'ArrayList ordini.

	@Override
	public int getSize() {
		return this.ordini.size();
	}

	//consegnaOrdine rimuove dalla lista l'ordine consegnato

	public void ordinePronto(int index){
		this.ordini.remove(index);
	}
	
}

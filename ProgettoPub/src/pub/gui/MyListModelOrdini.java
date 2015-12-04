package pub.gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataListener;

import pub.entita.Prodotti;

public class MyListModelOrdini extends AbstractListModel {

	private ArrayList<Prodotti> prodotti = null;
	
	public MyListModelOrdini(){	
		this.prodotti = new ArrayList<Prodotti>();
		this.prodotti.add(new Prodotti(-1," "," ",0));		
	}

	public ArrayList<Prodotti> getProdotti() {
		return prodotti;
	}


	public void addProdotti(Prodotti prodotto) {
		this.prodotti.add(prodotto);
		if(this.prodotti.get(0).getIdProdotto() == -1)
			this.prodotti.remove(0);	
	}
	
	public void deleteProdotto(int index){
		if(index >= 0)
			this.prodotti.remove(index);
		else
			prodotti.add(new Prodotti(-1," "," ",0));
	}

	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public String getElementAt(int index) {
		if(this.prodotti.get(index).getIdProdotto() == -1)
			return this.prodotti.get(index).getNome();
		String out = prodotti.get(index).getNome() + " " + prodotti.get(index).getCosto();
		return out;
	}

	@Override
	public int getSize() {
		return prodotti.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub	
	}


	public void resetta(){
		this.prodotti.clear();
		prodotti.add(new Prodotti(-1," "," ",0));
	}

}

package pub.gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import pub.entita.Ordinazione;
import pub.entita.Prodotto;

public class MyListModelOrdini extends AbstractListModel {

	private ArrayList<Prodotto> prodotti = null;

	private ArrayList<Ordinazione> ordinazioni = null;
	
	private static final int ID_PRODOTTO_INIZIALIZZAZIONE = -1;
	private static final int COSTO_INIZIALIZZAZIONE = 0;
	private static final int NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE = 1;
	private static final int NUMERO_PRODOTTI_DIVERSI_INIZIALIZZAZIONE = 0;
	private static final String DESCRIZIONE_INIZIALIZZAZIONE = " ";
	private static final String NOME_INIZIALIZZAZIONE = " ";

	//indice per sapere quanti prodotti uguali ci sono
	private int numeroProdottiUguali;
	private int numeroProdottiDiversi;

	public MyListModelOrdini(){	
		this.prodotti = new ArrayList<Prodotto>();
		this.ordinazioni = new ArrayList<Ordinazione>();
		this.prodotti.add(new Prodotto(ID_PRODOTTO_INIZIALIZZAZIONE, NOME_INIZIALIZZAZIONE, DESCRIZIONE_INIZIALIZZAZIONE, 
				COSTO_INIZIALIZZAZIONE));		
		this.numeroProdottiUguali = NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE;
		this.numeroProdottiDiversi = NUMERO_PRODOTTI_DIVERSI_INIZIALIZZAZIONE; 
	}

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public ArrayList<Ordinazione> getOrdinazioni(){
		return ordinazioni;
	}


	public void addProdotti(Prodotto prodotto) {

		if(!this.prodotti.contains(prodotto))
			this.ordinazioni.add(new Ordinazione(this.numeroProdottiUguali,prodotto.getCosto(), prodotto.getNome()));

		else{
			for (Ordinazione o: this.ordinazioni)
				if(o.getNome() == prodotto.getNome()){
					int i = this.ordinazioni.indexOf(o);
					this.ordinazioni.get(i).setQuantita(this.numeroProdottiUguali);
				}
		}

		this.prodotti.add(prodotto);

		if(this.prodotti.get(0).getIdProdotto() == ID_PRODOTTO_INIZIALIZZAZIONE)
			this.prodotti.remove(0);	
	}

	public void deleteProdotto(int index){
		if(index >= 0){
			Ordinazione appo = this.ordinazioni.get(index);
			boolean cancellato = false;
			for(int i=0;i<this.prodotti.size();i++){
				if(this.prodotti.get(i).getNome() == appo.getNome() && !cancellato){
					int indice = this.prodotti.indexOf(this.prodotti.get(i));
					this.prodotti.remove(indice);
					cancellato = true;
				}
			}

			if(this.ordinazioni.get(index).getQuantita() > 1){
				this.ordinazioni.get(index).setQuantita(this.ordinazioni.get(index).getQuantita()-1);
			}

			else{
				this.ordinazioni.remove(index);
				this.numeroProdottiDiversi--;
			}

		}
		else{
			prodotti.add(new Prodotto(ID_PRODOTTO_INIZIALIZZAZIONE, NOME_INIZIALIZZAZIONE, DESCRIZIONE_INIZIALIZZAZIONE, 
					COSTO_INIZIALIZZAZIONE));
		}
	}

	@Override
	public String getElementAt(int index) {
		if(this.prodotti.get(index).getIdProdotto() == ID_PRODOTTO_INIZIALIZZAZIONE)
			return this.prodotti.get(index).getNome();

		return this.ordinazioni.get(index).getQuantita() + "x " + this.ordinazioni.get(index).getNome() + " "
		+ this.ordinazioni.get(index).getCosto();
	}

	@Override
	public int getSize() {
		return this.numeroProdottiDiversi;
	}

	public void resetta(){
		this.prodotti.clear();
		this.ordinazioni.clear();
		this.numeroProdottiDiversi = NUMERO_PRODOTTI_DIVERSI_INIZIALIZZAZIONE;
		this.numeroProdottiUguali = NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE;
		prodotti.add(new Prodotto(ID_PRODOTTO_INIZIALIZZAZIONE, NOME_INIZIALIZZAZIONE, DESCRIZIONE_INIZIALIZZAZIONE, 
				COSTO_INIZIALIZZAZIONE));
	}

	public void numeroProdottiUguali(Prodotto p){
		this.numeroProdottiUguali = NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE;
		for(Prodotto prodotto: this.prodotti)
			if(prodotto.equals(p))
				this.numeroProdottiUguali++;	

	}

	public void numeroProdottiDiversi(Prodotto p){
		if(!this.prodotti.contains(p))
			this.numeroProdottiDiversi++;
	}

	public int getSizeOfProdotti(){
		return this.prodotti.size();
	}
}

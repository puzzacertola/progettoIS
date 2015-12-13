package pub.gui;

import java.util.ArrayList;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Modello utilizzato dalla JList degli Ordini.
 *
 */

import javax.swing.AbstractListModel;

import pub.entita.Ordinazione;
import pub.entita.Prodotto;

public class ModelloOrdini extends AbstractListModel {

	//In prodotti vengono salvati i prodotti selezionati durante un'ordinazione.
	private ArrayList<Prodotto> prodotti = null;

	//In ordinazioni vengono salvati i prodotti ordinati con le rispettive quantità per poi stamparli a video.
	private ArrayList<Ordinazione> ordinazioni = null;

	private static final int ID_PRODOTTO_INIZIALIZZAZIONE = -1;
	private static final int COSTO_INIZIALIZZAZIONE = 0;
	private static final int NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE = 1;
	private static final int NUMERO_PRODOTTI_DIVERSI_INIZIALIZZAZIONE = 0;
	private static final String DESCRIZIONE_INIZIALIZZAZIONE = " ";
	private static final String NOME_INIZIALIZZAZIONE = " ";

	//indici per sapere quanti prodotti uguali ci sono e quanti diversi.
	private int numeroProdottiUguali;
	private int numeroProdottiDiversi;

	public ModelloOrdini(){	
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

	/*
	 * addProdotti aggiunge all'ArrayList prodotti il prodotto selezionato nella JList delle Bevande o degli Snack.
	 * Verifica se l'array prodotti contiene gia un prodotto con lo stesso id. Nel caso non lo contiene, il prodotto
	 * viene aggiunto all'ArrayList ordinazioni, altrimenti viene modificato solo il campo quantità della rispettiva Ordinazioe.
	 */

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

	/*
	 * deleteProdotto elimina sia in prodotti che in ordinazioni il prodotto selezionato.
	 */

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

	/*
	 * getElementAt è il metodo utilizzato dalla JList per stampare le stringhe in un JScrollPane.
	 * Viene stampato a video il contenuto dell' ArrayList Ordinazioni.
	 * 
	 */

	@Override
	public String getElementAt(int index) {
		if(this.prodotti.get(index).getIdProdotto() == ID_PRODOTTO_INIZIALIZZAZIONE)
			return this.prodotti.get(index).getNome();

		return this.ordinazioni.get(index).getQuantita() + "x " + this.ordinazioni.get(index).getNome() + " "
		+ this.ordinazioni.get(index).getCosto();
	}

	//getSize Restituisce il numero di prodotti diversi presenti nell'ArrayList prodotti.

	@Override
	public int getSize() {
		return this.numeroProdottiDiversi;
	}

	//resetta tutti i campi del modello.

	public void resetta(){
		this.prodotti.clear();
		this.ordinazioni.clear();
		this.numeroProdottiDiversi = NUMERO_PRODOTTI_DIVERSI_INIZIALIZZAZIONE;
		this.numeroProdottiUguali = NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE;
		prodotti.add(new Prodotto(ID_PRODOTTO_INIZIALIZZAZIONE, NOME_INIZIALIZZAZIONE, DESCRIZIONE_INIZIALIZZAZIONE, 
				COSTO_INIZIALIZZAZIONE));
	}

	//numeroProdottiUguali ottiene il numero dei prodotti uguali presenti all'interno dell' ArrayList prodotti ogni volta che si aggiunge un Prodotto

	public void numeroProdottiUguali(Prodotto p){
		this.numeroProdottiUguali = NUMERO_PRODOTTI_UGUALI_INIZIALIZZAZIONE;
		for(Prodotto prodotto: this.prodotti)
			if(prodotto.equals(p))
				this.numeroProdottiUguali++;	

	}

	//numeroProdottiDiversi ottiene il numero dei prodotti diversi presenti all'interno dell' ArrayList prodotti ogni volta che si aggiunge un Prodotto

	public void numeroProdottiDiversi(Prodotto p){
		if(!this.prodotti.contains(p))
			this.numeroProdottiDiversi++;
	}

	//getSizeOfProdotti restituisce la dimensione dell' ArrayList prodotti.

	public int getSizeOfProdotti(){
		return this.prodotti.size();
	}
}

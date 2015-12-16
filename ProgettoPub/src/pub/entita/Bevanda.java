package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Sottoclasse di Prodotto.
 * Una bevanda è un particolare prodotto del Menu del giorno. 
 * Può essere alcolica o analcolica (specificato nella tabella Menu del database).
 *
 */

public class Bevanda extends Prodotto {
	public Bevanda(int id, String nome, String descrizione, float costo, String tipo){		
		super(id,nome,descrizione,costo,tipo);				
	}

	public Bevanda(int id, String nome, String descrizione, float costo){		
		super(id,nome,descrizione,costo);			
	}

}

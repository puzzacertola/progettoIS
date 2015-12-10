package pub.entita;

public class Bevanda extends Prodotto {
	public Bevanda(int id, String nome, String descrizione, float costo, String tipo){		
		super(id,nome,descrizione,costo,tipo);				
	}

	public Bevanda(int id, String nome, String descrizione, float costo){		
		super(id,nome,descrizione,costo);			
	}
}

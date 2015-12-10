package pub.entita;

public class Snack extends Prodotto {	
	public Snack(int id, String nome, String descrizione, float costo, String tipo){
		super(id,nome,descrizione,costo,tipo);				
	}
	
	public Snack(int id, String nome, String descrizione, float costo){		
		super(id,nome,descrizione,costo);			
	}
}

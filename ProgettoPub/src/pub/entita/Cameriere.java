package pub.entita;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Un cameriere ha i dati salvati all'interno della tabella Camerieri del database.
 *
 */

public class Cameriere {
	//Istanze
	static final int PORTA = 80;
	private int IdCameriere;
	private String Nome;
	private String Cognome;

	//Costruttore
	public Cameriere(){

	}

	//get and setters

	public int getIdCameriere() {
		return IdCameriere;
	}

}


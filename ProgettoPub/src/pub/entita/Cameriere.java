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
	/**
	 * @uml.property  name="idCameriere"
	 */
	private int IdCameriere;
	/**
	 * @uml.property  name="nome"
	 */
	private String Nome;
	/**
	 * @uml.property  name="cognome"
	 */
	private String Cognome;

	//Costruttore
	public Cameriere(){

	}

	//get and setters

	/**
	 * @return
	 * @uml.property  name="idCameriere"
	 */
	public int getIdCameriere() {
		return IdCameriere;
	}

}


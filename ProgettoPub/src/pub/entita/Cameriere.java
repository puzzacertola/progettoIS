package pub.entita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class Cameriere {
	
	//Istanze
	static final int PORTA = 80;
	private int IdCameriere;
	private String Nome;
	private String Cognome;
	private String operazione;
	
	//Costruttore
	public Cameriere(){
		
	}

	//get and setters

	public int getIdCameriere() {
		return IdCameriere;
	}

	public void setIdCameriere(int idCameriere) {
		IdCameriere = idCameriere;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public static void main (String[] args){
		
		Cameriere client =new Cameriere();
		
		try {
			Socket s = new Socket ("localhost" , PORTA);

			PrintWriter out = new PrintWriter (s.getOutputStream(),true);
			BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));
			
			
			out.println("select * from Menu");
			String rcvd =in.readLine ();
			System.out.println(rcvd);

			s.close();
		}
		catch (IOException e){
			System.err.println(e.getMessage());	
		}
		
		
	}
	
	
}
	

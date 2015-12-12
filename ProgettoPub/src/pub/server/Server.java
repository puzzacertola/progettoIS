package pub.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import pub.dao.DAOCameriereImpl;
import pub.entita.Bevanda;
import pub.entita.Ordine;
import pub.entita.Snack;

/**
 * @authors Giuseppe, Giovanni
 *
 * Parte server del progetto.
 * 
 */

public class Server {
	public static final int PORTA = 80;
	public static final String USER = "server";
	public static final String PASS = "server";
	public static final String HOST = "localhost";
	public static final String SELECT_CAMERIERE_MENU_BEVANDE = "req_select_cameriere_menu_bevande";
	public static final String SELECT_CAMERIERE_MENU_SNACK = "req_select_cameriere_menu_snack";
	public static final String INSERT_CAMERIERE_ORDINI = "req_insert_cameriere_ordini";
	public static final String SELECT_CAMERIERE_ORDINI = "req_select_cameriere_ordini";
	public static final String DELETE_CAMERIERE_ORDINI = "req_delete_cameriere_ordini";
	public static final String UPDATE_CAMERIERE_ORDINI = "req_update_cameriere_ordini";
	public static final String SELECT_CAMERIERE_IDORDINI = "req_select_cameriere_idordini";
	public static final String SELECT_CAMERIERE_ORDINI_CAMERIERE = "req_select_cameriere_ordini_cameriere";
	public static final String SELECT_CAMERIERE_ORDINI_TAVOLO = "req_select_cameriere_ordini_tavolo";

	//rispostaBevande restituisce la risposta da inviare alla richiesta di una select delle bevande nella tabella Menu
	
	public static String rispostaBevande(List<Bevanda> lista){
		String risposta = "";

		for(Bevanda b: lista){
			risposta += b.getIdProdotto() + ";";
			risposta += b.getNome() + ";";
			risposta += b.getDescrizione() + ";";
			risposta += b.getCosto() + "\n";					
		}

		return risposta;
	}
	
	//rispostaSnack restituisce la risposta da inviare alla richiesta di una select degli snack nella tabella Menu

	public static String rispostaSnack(List<Snack> lista){
		String risposta = "";

		for(Snack s: lista){
			risposta += s.getIdProdotto() + ";";
			risposta += s.getNome() + ";";
			risposta += s.getDescrizione() + ";";
			risposta += s.getCosto() + "\n";					
		}

		return risposta;
	}
	
	//rispostaOrdini restituisce la risposta da inviare alla richiesta di una select nella tabella Ordini

	public static String rispostaOrdini(List<Ordine> lista){
		String risposta = "";

		for(Ordine o: lista){
			risposta += o.getIdOrdine() + ";";
			risposta += o.getIdProdotto() + ";";
			risposta += o.getTavolo() + ";";
			risposta += o.getIdCameriere() + ";";
			risposta += o.getStato() + "\n";
		}

		return risposta;
	}
	
	//rispostaOrdiniCameriere restituisce la risposta da inviare alla richiesta di una select nella tabella Ordini col vincolo di un cameriere
	
	public static String rispostaOrdiniCameriere(List<Ordine> lista){
		String risposta = "";

		for(Ordine o: lista){
			risposta += o.getIdOrdine() + ";";
			risposta += o.getNomeProdotto() + ";";
			risposta += o.getTavolo() + ";";
			risposta += o.getStato() + "\n";
		}

		return risposta;
	}

	public static void main (String[] args) throws Exception{
		String protocollo = "";
		String risposta = "";
		ServerSocket ss = new ServerSocket(PORTA);
		while(true){
			try {

				System.out.println("Server in attesa");

				Socket s = ss.accept();

				PrintWriter out = new PrintWriter (s.getOutputStream(),true);
				BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));

				protocollo = in.readLine();
				String comando = in.readLine();
				
				//Query di selezione delle bevande fatta da un cameriere.

				if(comando.equals(SELECT_CAMERIERE_MENU_BEVANDE)){
					List<Bevanda> lista = DAOCameriereImpl.getInstance().mostraBevande();

					risposta = rispostaBevande(lista);

					out.println(risposta);
				}

				//Query di selezione degli snack fatta da un cameriere.
				
				else if(comando.equals(SELECT_CAMERIERE_MENU_SNACK)){
					List<Snack> lista = DAOCameriereImpl.getInstance().mostraSnack();

					risposta = rispostaSnack(lista);

					out.println(risposta);
				}
				
				//Query di insert nella tabella Ordini fatta da un cameriere.

				else if(comando.equals(INSERT_CAMERIERE_ORDINI)){

					int idProdotto = Integer.parseInt(in.readLine().replace("id:", ""));
					int tavolo = Integer.parseInt(in.readLine().replace("tavolo:", ""));
					int idCameriere = Integer.parseInt(in.readLine().replace("idCameriere:", ""));

					Ordine ordine = new Ordine(idProdotto, tavolo, idCameriere, "Da Fare");

					DAOCameriereImpl.getInstance().inserisciOrdini(ordine);
				}
				
				//Query di selezione degli Ordini fatta da un cameriere.

				else if(comando.equals(SELECT_CAMERIERE_ORDINI)){
					List<Ordine> lista = DAOCameriereImpl.getInstance().mostraOrdini();

					risposta = rispostaOrdini(lista);

					out.println(risposta);
				}
				
				//Query di delete di un ordine fatta da un cameriere.

				else if(comando.equals(DELETE_CAMERIERE_ORDINI)){
					int idOrdine = Integer.parseInt(in.readLine().replace("id:", ""));

					DAOCameriereImpl.getInstance().eliminaOrdine(idOrdine);
				}
				
				//Query di update di un ordine fatta da un cameriere.

				else if(comando.equals(UPDATE_CAMERIERE_ORDINI)){

					int idOrdine = Integer.parseInt(in.readLine().replace("idOrdine:", ""));
					String stato = in.readLine().replace("stato:", "");

					DAOCameriereImpl.getInstance().modificaOrdine(idOrdine, stato);
				}
				
				//Query di selezione di un Ordine fatta da un Cameriere.

				else if(comando.equals(SELECT_CAMERIERE_IDORDINI)){
					List<Ordine> lista = DAOCameriereImpl.getInstance().mostraOrdini();

					for(Ordine o: lista)
						risposta += o.getIdOrdine() + "\n";

					out.println(risposta);
				}
				
				//Query di selezione degli Ordini fatti da un cameriere, fatta da un cameriere.

				else if(comando.equals(SELECT_CAMERIERE_ORDINI_CAMERIERE)){
					int idCameriere = Integer.parseInt(in.readLine().replace("id:", ""));
					List<Ordine> lista = DAOCameriereImpl.getInstance().mostraOrdiniCameriere(idCameriere);

					risposta = rispostaOrdiniCameriere(lista);

					out.println(risposta);
				}
				
				//Query di selezione degli ordini fatti ad un tavolo, fatta da un cameriere.

				else if(comando.equals(SELECT_CAMERIERE_ORDINI_TAVOLO)){
					int tavolo = Integer.parseInt(in.readLine().replace("id:", ""));
					List<Ordine> lista = DAOCameriereImpl.getInstance().mostraOrdiniTavolo(tavolo);

					risposta = rispostaOrdini(lista);

					out.println(risposta);
				}

				s.close();

			} catch(IOException e){
				e.printStackTrace();	
				System.out.println("Errore nella comunicazione");
			}
			
		}
	}

}

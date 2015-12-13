package pub.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pub.server.Server;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Settaggi della Gui del cassiere.
 *
 */

public class CassiereGuiSetting { 

	/* ottieniStringaDalDatabase riceve come parametro la richiesta di query di select da inviare al server.
	 * Ottiene la stringa di risposta dal server e la splitta per ogni tupla della tabella del database.
	 */

	public static String ottieniStringaDalDatabase(String req){
		Socket s;
		try {
			s = new Socket(Server.HOST, Server.PORTA);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			out.println(req);

			String line = in.readLine();
			String risposta = line;

			while(line.length() > 0){
				line = in.readLine();
				risposta += "\n" + line;
				s.close();
			}

			return risposta;

		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore nella connessione al server");
		}
		return null;
	}

	/* ottieniTotaleDalDataBase riceve come parametro la richiesta di query di select da inviare al server.
	 * Ottiene la stringa di risposta, che rappresenta il totale da pagare da un tavolo.
	 */

	public static String ottieniTotaleDalDataBase(String req){
		Socket s;
		try {
			s = new Socket(Server.HOST, Server.PORTA);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			out.println(req);

			return in.readLine();

		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore nella connessione al server");
		}
		return null;
	}

	/* Listener del TextField Tavolo. Visualizza nella Gui
	 * le ordinazioni consegnate ad un Tavolo e mostra il conto da pagare.
	 */

	public static class TavoloTextFieldListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {}

		@Override
		public void insertUpdate(DocumentEvent e) {
			if(!CassiereGui.tavoloTextField.getText().equals("")){

				String req = "pub:\n" + Server.SELECT_CASSIERE_ORDINI + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				String risposta = ottieniStringaDalDatabase(req);
				CassiereGui.contoTextArea.append(risposta);
				req = "pub:\n" + Server.SELECT_CASSIERE_TOTALE + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				risposta = ottieniTotaleDalDataBase(req);
				CassiereGui.contoTextArea.append("Totale: " + risposta + " €");
			}
			else
				JOptionPane.showMessageDialog(new JFrame(), "Errore: id Cameriere errato" 
						+ "Controllare che tutti i campi sono corretti!", "Errore", JOptionPane.ERROR_MESSAGE);

		}

		@Override
		public void removeUpdate(DocumentEvent e) {}
	}

}

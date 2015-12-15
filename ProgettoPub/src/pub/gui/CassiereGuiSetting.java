package pub.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public static void mandaDeleteAlServer(String req){
		Socket s;
		try {
			s = new Socket(Server.HOST, Server.PORTA);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println(req);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errore nella connessione al server");
		}
	}

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
	 * verifica che nel campo tavolo sia inserito un valore numerico
	 */

	public static class TavoloTextFieldListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			try{
				Integer.parseInt(CassiereGui.tavoloTextField.getText());}
			catch (NumberFormatException ex){
				ex.getMessage();
				JOptionPane.showMessageDialog(new JFrame(),"Nel campo tavolo va inserito un valore numerico", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!CassiereGui.tavoloTextField.getText().equals("")){

				String req = "pub:\n" + Server.SELECT_CASSIERE_ORDINI + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				String risposta = ottieniStringaDalDatabase(req);
				CassiereGui.contoTextArea.setText("");
				CassiereGui.contoTextArea.append(risposta);
				req = "pub:\n" + Server.SELECT_CASSIERE_TOTALE + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				risposta = ottieniTotaleDalDataBase(req);
				CassiereGui.totaleTextArea.setText("");
				CassiereGui.totaleTextArea.append(risposta + " €");
			}
			
			else
				JOptionPane.showMessageDialog(new JFrame(), "Errore: id Cameriere errato" 
						+ "Controllare che tutti i campi sono corretti!", "Errore", JOptionPane.ERROR_MESSAGE);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			try{
				Integer.parseInt(CassiereGui.tavoloTextField.getText());}
			catch (NumberFormatException ex){
				ex.getMessage();
				JOptionPane.showMessageDialog(new JFrame(),"Nel campo tavolo va inserito un valore numerico", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!CassiereGui.tavoloTextField.getText().equals("")){

				String req = "pub:\n" + Server.SELECT_CASSIERE_ORDINI + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				String risposta = ottieniStringaDalDatabase(req);
				CassiereGui.contoTextArea.setText("");
				CassiereGui.contoTextArea.append(risposta);
				req = "pub:\n" + Server.SELECT_CASSIERE_TOTALE + "\nTavolo:" + CassiereGui.tavoloTextField.getText();
				risposta = ottieniTotaleDalDataBase(req);
				CassiereGui.totaleTextArea.setText("");
				CassiereGui.totaleTextArea.append(risposta + " €");
			}
			
			else
				JOptionPane.showMessageDialog(new JFrame(), "Errore: id Cameriere errato" 
						+ "Controllare che tutti i campi sono corretti!", "Errore", JOptionPane.ERROR_MESSAGE);
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {}
		
	}
	
	// elimina dal database tutti gli ordini del tavolo che ha effettuato il pagamento
	
	public static class MyButtonPagatoListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String req = "pub:\n" + Server.DELETE_CASSIERE_ORDINI + "\ntavolo:" 
					+ CassiereGui.tavoloTextField.getText() + "\n";
			mandaDeleteAlServer(req);
			JOptionPane.showMessageDialog(new JFrame(), "Ordine Pagato!");
			CassiereGui.contoTextArea.setText("");
		}
	}
	

}

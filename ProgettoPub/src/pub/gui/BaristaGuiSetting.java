package pub.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pub.server.Server;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Settaggi della Gui del Barista.
 *
 */

abstract class BaristaGuiSetting {

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

	//mandaUpdateAlServer riceve come parametro la richiesta di query di update da fare e la manda al server

	public static void mandaUpdateAlServer(String req){
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

	/* Listener della selezione di un campo della JList degli Ordini.
	 * Segna un Ordine come "Pronto".
	 */

	public static class OrdiniBaristaSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				int index = BaristaGui.jListOrdini.locationToIndex(evt.getPoint());

				if(index >= 0){
					int n = JOptionPane.showConfirmDialog(new JFrame(),"Segnare ordine come Fatto?"
							,"Ordine Pronto",JOptionPane.YES_NO_OPTION);

					if (n == 0){
						if (BaristaGui.jListOrdini.getSelectedIndex() != -1) {
							String req = "pub:\n" + Server.UPDATE_BARISTA_ORDINI + "\nNome:" 
									+ BaristaGui.modelloOrdini.getOrdini().get(index).getNomeProdotto() +"\nTavolo:"
									+ BaristaGui.modelloOrdini.getOrdini().get(index).getTavolo()
									+ "\nStato:Fatto\n";

							mandaUpdateAlServer(req);

							BaristaGui.modelloOrdini.ordinePronto(index);
							BaristaGui.jListOrdini.updateUI();
						}
					}
				}
			}
		}
	}

}

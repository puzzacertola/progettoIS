package pub.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import pub.server.Server;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Settaggi della Gui degli ordini.
 *
 */

abstract class OrdiniGuiSetting {

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
	 * Quando viene selezionato un ordine, viene chiesto se questo deve essere segnato come "Consegnato".
	 * Se l'ordine viene segnato come "Consegnato", viene generata una richiesta di 
	 * query di update per modificare lo stato dell'ordine.
	 */

	public static class StatoOrdiniSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				int index = OrdiniGui.jListOrdini.locationToIndex(evt.getPoint());
				
				if(index >= 0){
					int n = JOptionPane.showConfirmDialog(new JFrame(),"Segnare ordine come consegnato?"
							,"Consegna Ordine",JOptionPane.YES_NO_OPTION);
					
					if (n == 0){
						if (OrdiniGui.jListOrdini.getSelectedIndex() != -1) {
							String req = "pub:\n" + Server.UPDATE_CAMERIERE_ORDINI + "\nidOrdine:" 
									+ OrdiniGui.modelloStatoOrdini.getOrdini().get(index).getIdOrdine()
									+ "\nstato:Consegnato\n";

							mandaUpdateAlServer(req);

							OrdiniGui.modelloStatoOrdini.consegnaOrdine(index);
							OrdiniGui.jListOrdini.updateUI();
						}
					}
				}
			}
		}
	}
	
}

package pub.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Settaggi della Gui del cameriere.
 *
 */

abstract class CameriereGuiSetting {
	
	//mandaInsertAlServer riceve come parametro la query di insert da fare e la manda al server

	public static void mandaInsertAlServer(String req){
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
	
	/* ottieniStringaDalDatabase riceve come parametro la query di select da inviare al server.
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errore nella connessione al server");
		}
		return null;
	}
	
	// Listener del bottone reset. Resetta la Jlist degli Ordini correnti.

	public static class MyButtonResetListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			CameriereGui.modelloOrdini.resetta();
			CameriereGui.jListOrdini.updateUI();

		}
	}

	/* Listener del bottone StatoOrdini. Visualizza nella Gui OrdiniGui, le ordinazioni fatte da un cameriere segnate
	 * come "Pronto" presenti nella tabella Ordini.
	 */
	
	public static class StatoOrdiniButton implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("statoOrdini pressed \n");
			String req = "pub:\n" + Server.SELECT_CAMERIERE_ORDINI_CAMERIERE + "\nid:" + CameriereGui.idCameriereTextField.getText() + "\n";
			String risposta = CameriereGuiSetting.ottieniStringaDalDatabase(req);
			new OrdiniGui(risposta);
		}
	}

	/* Listener bottone invia. Controlla se tutti i campi sono inseriti correttamente. 
	 * Fa un insert di ogni ordine presente nella JList Ordini. 	
	 */
	
	public static class MyButtonInviaListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			boolean inserito = true;
			if(CameriereGui.modelloOrdini.getProdotti().get(0).getIdProdotto() != -1 && 
					!CameriereGui.idCameriereTextField.getText().equals("id") && 
					!CameriereGui.tavoloTextField.getText().equals("")){

				for(int i=0;i<CameriereGui.modelloOrdini.getSizeOfProdotti();i++){

					String req = "pub:\n" + Server.INSERT_CAMERIERE_ORDINI + "\nid:" 
							+ CameriereGui.modelloOrdini.getProdotti().get(i).getIdProdotto() 
							+ "\ntavolo:" + CameriereGui.tavoloTextField.getText() + "\nidCameriere:" 
							+ CameriereGui.idCameriereTextField.getText() + "\n";
					CameriereGuiSetting.mandaInsertAlServer(req);										 					

				}

			}else
				inserito = false;

			if(inserito == false)
				JOptionPane.showMessageDialog(new JFrame(), "Errore nell'inserimento."
						+ "Controllare che tutti i campi sono corretti!", "Errore", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Ordine inserito correttamente!");
		}
	}

	/* Listener della selezione di un campo della JList delle Bevande.
	 * Aggiunge una bevanda alla JList degli ordini, e la salva in memoria.
	 */
	
	public static class BevandeSelezioneListener extends MouseAdapter {	

		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				if (CameriereGui.jListBevande.getSelectedIndex() != -1) {
					int index = CameriereGui.jListBevande.locationToIndex(evt.getPoint());
					CameriereGui.modelloOrdini.numeroProdottiUguali(CameriereGui.modelloBevande.getBevande().get(index));
					CameriereGui.modelloOrdini.numeroProdottiDiversi(CameriereGui.modelloBevande.getBevande().get(index));
					CameriereGui.modelloOrdini.addProdotti(CameriereGui.modelloBevande.getBevande().get(index));
					CameriereGui.jListOrdini.updateUI();
				}
			}
		}
	}
	
	/* Listener della selezione di un campo della JList degli Ordini.
	 * Quando viene selezionato un ordine, viene chiesto se questo deve essere cancellato.
	 */

	public static class OrdiniSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				int index = CameriereGui.jListOrdini.locationToIndex(evt.getPoint());
				if(index >= 0){
					int n = JOptionPane.showConfirmDialog(new JFrame(),"Cancellare " 
							+ CameriereGui.modelloOrdini.getOrdinazioni().get(index).getNome() + "?"
							,"Eliminazione Ordine",JOptionPane.YES_NO_OPTION);
					if (n == 0){
						if (CameriereGui.jListOrdini.getSelectedIndex() != -1) {
							CameriereGui.modelloOrdini.deleteProdotto(index);   
							CameriereGui.jListOrdini.updateUI();
						}
					}
				}

			}
		}
	}

	/* Listener della selezione di un campo della JList degli Snack.
	 * Aggiunge uno snack alla JList degli ordini, e lo salva in memoria.
	 */
	
	public static class SnackSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				if (CameriereGui.jListSnack.getSelectedIndex() != -1) {
					int index = CameriereGui.jListSnack.locationToIndex(evt.getPoint());
					CameriereGui.modelloOrdini.numeroProdottiUguali(CameriereGui.modelloSnack.getSnack().get(index));
					CameriereGui.modelloOrdini.numeroProdottiDiversi(CameriereGui.modelloSnack.getSnack().get(index));
					CameriereGui.modelloOrdini.addProdotti(CameriereGui.modelloSnack.getSnack().get(index));
					CameriereGui.jListOrdini.updateUI();
				}
			}
		}
	}






}
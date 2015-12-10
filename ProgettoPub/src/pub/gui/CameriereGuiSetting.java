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

abstract class CameriereGuiSetting {

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

	public static class MyButtonResetListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			CameriereGui.modelloOrdini.resetta();
			CameriereGui.jListOrdini.updateUI();

		}
	}

	//ascoltatore pulsante StatoOrdini
	public static class StatoOrdiniButton implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("statoOrdini pressed \n");
			String req = "pub:\n" + Server.SELECT_CAMERIERE_ORDINI_CAMERIERE + "\nid:" + CameriereGui.idCameriereTextField.getText() + "\n";
			String risposta = CameriereGuiSetting.ottieniStringaDalDatabase(req);
			new OrdiniGui(risposta);
		}
	}

	//ascoltatore pulsante invia
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

	//listener selezione bevande

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

	//Listner selezione snack
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
package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pub.server.Server;

public class CameriereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static JTextField tavoloTextField = new JTextField("",5);
	private static JTextField idCameriereTextField = new JTextField("id",5);
	private static JButton inviaButton = new JButton("Invia");
	private static JButton resetButton = new JButton("Reset");	
	private static Container pane;
	private static MyListModelBevanda modelloBevande = null;
	private static MyListModelSnack modelloSnack = null;
	private static MyListModelOrdini modelloOrdini = null;
	private static JButton statoOrdiniButton = new JButton("Visualizza ordini");

	static JList jListBevande = new JList();
	static JList jListSnack = new JList();
	static JList jListOrdini = new JList();

	//ascoltatore pulsante StatoOrdini
	private static class StatoOrdiniButton implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("statoOrdini pressed \n");
			String req = "pub:\n" + Server.SELECT_CAMERIERE_ORDINI_CAMERIERE + "\nid:" + idCameriereTextField.getText() + "\n";
			String risposta = ottieniStringaDalDatabase(req);
			new OrdiniGui(risposta);
		}
	}

	//ascoltatore pulsante invia
	private static class MyButtonInviaListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			boolean inserito = true;
			if(/*modelloOrdini.getProdotti().get(0).getNome() == "" ||*/ idCameriereTextField.getText() != "id" /*|| tavoloTextField.getText() == ""*/){
				for(int i=0;i<modelloOrdini.getSize();i++){
					String req = "pub:\n" + Server.INSERT_CAMERIERE_ORDINI + "\nid:" + modelloOrdini.getProdotti().get(i).getIdProdotto() 
							+ "\ntavolo:" + tavoloTextField.getText() + "\nidCameriere:" + idCameriereTextField.getText() + "\n";
					mandaInsertAlServer(req);										 					
				}
			}else
				inserito = false;

			if(inserito == false)
				JOptionPane.showMessageDialog(new JFrame(), "Errore nell'inserimento. "
						+ "Controllare che tutti i campi sono corretti!", "Errore", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(new JFrame(), "Ordine inserito correttamente!");


		}
	}
	//ascoltatore pulsante ok
	private static class MyButtonResetListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			modelloOrdini.resetta();
			jListOrdini.updateUI();
		}
	}

	//listener selezione bevande

	public static class BevandeSelezioneListener extends MouseAdapter {	
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				if (jListBevande.getSelectedIndex() != -1) {
					int index = jListBevande.locationToIndex(evt.getPoint());
					modelloOrdini.addProdotti(modelloBevande.getBevande().get(index));
					jListOrdini.updateUI();
				}
			}
		}
	}

	//Listner selezione snack
	public static class SnackSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				if (jListSnack.getSelectedIndex() != -1) {
					int index = jListSnack.locationToIndex(evt.getPoint());
					modelloOrdini.addProdotti(modelloSnack.getSnack().get(index));
					jListOrdini.updateUI();
				}
			}
		}
	}

	public static class OrdiniSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				int index = jListOrdini.locationToIndex(evt.getPoint());
				int n = JOptionPane.showConfirmDialog(new JFrame(),"Cancellare " 
						+ modelloOrdini.getProdotti().get(index).getNome() + "?"
						,"Eliminazione Ordine",JOptionPane.YES_NO_OPTION);
				if (n == 0){
					if (jListOrdini.getSelectedIndex() != -1) {
						modelloOrdini.deleteProdotto(index);   
						jListOrdini.updateUI();
					}
				}

			}
		}
	}

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

	//creazione interfaccia
	public CameriereGui(){

		super("modulo ordinazioni");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("cameriere:"), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 4;
		c.gridy =0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(idCameriereTextField, c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Ordini"), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Bevande"), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Snack"), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListOrdini(),c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListBevande(),c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListSnack(), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("tavolo:"), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(tavoloTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(inviaButton, c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(resetButton, c);

		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel (" "), c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(statoOrdiniButton, c);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);

		MyButtonInviaListener inviaListener = new MyButtonInviaListener();
		inviaButton.addActionListener(inviaListener);
		MyButtonResetListener resetListner = new MyButtonResetListener();
		resetButton.addActionListener(resetListner);
		StatoOrdiniButton statoOrdiniListner= new StatoOrdiniButton();
		statoOrdiniButton.addActionListener(statoOrdiniListner);

	}

	//creazione JList
	private static JScrollPane getListBevande(){
		jListBevande = new JList(modelloBevande);

		JScrollPane pane = new JScrollPane(jListBevande);

		BevandeSelezioneListener ml = new BevandeSelezioneListener();

		jListBevande.addMouseListener(ml);

		return pane;
	}

	private static JScrollPane getListSnack(){
		jListSnack = new JList(modelloSnack);

		JScrollPane pane = new JScrollPane(jListSnack);

		SnackSelezioneListener ml = new SnackSelezioneListener();

		jListSnack.addMouseListener(ml);

		return pane;
	}

	private static JScrollPane getListOrdini(){
		jListOrdini = new JList(modelloOrdini);

		JScrollPane pane = new JScrollPane(jListOrdini);

		OrdiniSelezioneListener ml = new OrdiniSelezioneListener();

		jListOrdini.addMouseListener(ml);

		return pane;

	}


	public static void main(String[] args) {
		String risposta = null;
		String req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_BEVANDE;

		risposta = ottieniStringaDalDatabase(req);

		modelloBevande = new MyListModelBevanda(risposta);

		req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_SNACK;

		risposta = ottieniStringaDalDatabase(req);

		modelloSnack = new MyListModelSnack(risposta);

		modelloOrdini = new MyListModelOrdini();


		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {

			ex.printStackTrace();

		} catch (IllegalAccessException ex) {

			ex.printStackTrace();

		} catch (InstantiationException ex) {

			ex.printStackTrace();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		//new CameriereGui();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CameriereGui();
			}
		}); 


	}

}

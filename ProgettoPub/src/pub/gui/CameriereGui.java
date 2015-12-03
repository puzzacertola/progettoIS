package pub.gui;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import pub.entita.MyModelBevanda;
import pub.entita.MyModelOrdini;
import pub.entita.MyModelSnack;
import pub.server.Server;


public class CameriereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static JTextField tavolo = new JTextField("",5);
	private static JTextField idcameriere = new JTextField("",5);
	private static JButton invia = new JButton("Invia");
	private static JButton reset = new JButton("Reset");	
	private static String[] listaBevande = null;
	private static String[] listaSnack = null;
	private static String[] listaOrdini = new String[20];
	private static Container pane;
	private static MyModelBevanda modelloBevande = new MyModelBevanda();
	private static MyModelSnack modelloSnack = new MyModelSnack();
	private static MyModelOrdini modelloOrdini = new MyModelOrdini();
	private static int indiceListaOrdini = 0;
	private static JButton statoOrdini = new JButton("Visualizza ordini");
	
	static JList<String> jListBevande = new JList<String>();
	static JList<String> jListSnack = new JList<String>();
	static JList<String> jListOrdini = new JList<String>();
	
	
	private static class MyButtonstatoordini implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
				System.out.print("statoOrdini pressed \n");
			}
		}
	//ascoltatore pulsante invia
	private static class MyButtonOkListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("ok pressed \n");
			for(int i=0;i<indiceListaOrdini;i++){
				if(listaOrdini != null){
					String req = "pub:\n" + Server.INSERT_CAMERIERE_ORDINI + "\nid:" + modelloOrdini.getProdotto().get(i).getIdProdotto() 
							+ "\ntavolo:" + tavolo.getText() + "\nidCameriere:" + idcameriere.getText() + "\n";
					mandaInsertAlServer(req);
				}
			}
			
		}
	}
	//ascoltatore pulsante reset
	private static class MyButtonResetListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			for(int i=0;i<listaOrdini.length;i++)
				listaOrdini[i] = " ";
			indiceListaOrdini = 0;
			pane.repaint();
		}
	}
	
	//listener selezione bevande
	
	public static class BevandeSelezioneListener implements MouseListener{		
		@Override
	    public void mouseClicked(MouseEvent evt) {
	        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
	            if (jListBevande.getSelectedIndex() != -1) {
	                int index = jListBevande.locationToIndex(evt.getPoint());
	                listaOrdini[indiceListaOrdini] = modelloOrdini.addProdotti(modelloBevande.getBevanda().get(index));
	    			pane.repaint();
	    			indiceListaOrdini++;
	            }
	        }
	    }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//Listner selezione snack
	public static class SnackSelezioneListener implements MouseListener{		
		@Override
	    public void mouseClicked(MouseEvent evt) {
	        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
	            if (jListSnack.getSelectedIndex() != -1) {
	                int index = jListSnack.locationToIndex(evt.getPoint());
	                listaOrdini[indiceListaOrdini] = modelloOrdini.addProdotti(modelloSnack.getSnack().get(index));
	    			pane.repaint();
	    			indiceListaOrdini++;
	            }
	        }
	    }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class OrdiniSelezioneListener implements MouseListener{		
		@Override
	    public void mouseClicked(MouseEvent evt) {
	        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
	            if (jListOrdini.getSelectedIndex() != -1) {
	                int index = jListOrdini.locationToIndex(evt.getPoint());
	                listaOrdini[index] = null;
	    			pane.repaint();
	    			indiceListaOrdini--;
	            }
	        }
	    }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void mandaInsertAlServer(String req){
		Socket s;
		try {
			s = new Socket(Server.HOST, Server.PORTA);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			out.println(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		pane.add(idcameriere, c);

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
		pane.add(getListOrdini(listaOrdini),c);

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
		pane.add(getListBevande(listaBevande),c);

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
		pane.add(getListSnack(listaSnack), c);

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
		pane.add(tavolo, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(invia, c);

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
		pane.add(reset, c);

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
		pane.add(statoOrdini, c);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		MyButtonOkListener listener = new MyButtonOkListener();
		invia.addActionListener(listener);
		MyButtonResetListener listener1 = new MyButtonResetListener();
		reset.addActionListener(listener1);
		MyButtonstatoordini listener2= new MyButtonstatoordini();
    	statoOrdini.addActionListener(listener2);

	}
	//creazione JList
	private static JScrollPane getListBevande(String[] lista){
		jListBevande = new JList<String>(lista);
		JScrollPane pane = new JScrollPane(jListBevande);

		BevandeSelezioneListener ml = new BevandeSelezioneListener();
		  
		jListBevande.addMouseListener(ml);
		
		return pane;
	}
	
	private static JScrollPane getListSnack(String[] lista){
		jListSnack = new JList<String>(lista);
		JScrollPane pane = new JScrollPane(jListSnack);
		SnackSelezioneListener selezione = new SnackSelezioneListener();
		jListSnack.addMouseListener(selezione);
		
		return pane;
	}

	private static JScrollPane getListOrdini(String[] lista){
		jListOrdini = new JList<String>(lista);
		JScrollPane pane = new JScrollPane(jListOrdini);
		OrdiniSelezioneListener selezione = new OrdiniSelezioneListener();
		jListOrdini.addMouseListener(selezione);
		
		return pane;
	}


	public static void main(String[] args) {
		String risposta = null;
		String req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_BEVANDE;

		for(int i=0;i<listaOrdini.length;i++)
			listaOrdini[i] = " ";
		risposta = ottieniStringaDalDatabase(req);

		modelloBevande.addProdotti(risposta);

		listaBevande = modelloBevande.creaLista();

		req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_SNACK;

		risposta = ottieniStringaDalDatabase(req);

		modelloSnack.addProdotti(risposta);

		listaSnack = modelloSnack.creaLista();

		


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

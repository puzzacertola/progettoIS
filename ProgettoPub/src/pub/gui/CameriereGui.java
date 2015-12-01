package pub.gui;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pub.entita.MyModelBevanda;
import pub.entita.MyModelSnack;
import pub.server.Server;


public class CameriereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField tavolo = new JTextField(" ",5);
	private JTextField idcameriere = new JTextField(" ",5);
	private static JButton invia = new JButton("Invia");
	private static JButton reset = new JButton("Reset");	
	private static String[] listaBevande = null;
	private static String[] listaSnack = null;
	private static String[] listaOrdini = {""};
	
//ascoltatore pulsante invia
	private static class MyButtonokListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
				System.out.print("ok pressed \n");
		}
		}
//ascoltatore puòsante reset
	private static class MyButtonResetListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
				listaOrdini=null;
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
			}
			
			s.close();
			
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
		Container pane = getContentPane();
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
		pane.add(getList(listaOrdini),c);
        		
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
		pane.add(getList(listaBevande),c);
		
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
		pane.add(getList(listaSnack), c);
		
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
		
		   
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	//creazione JList
	   private static JScrollPane getList(String[] a){
		   //creare listmodel personalizzato
		   
	        JList<String> list = new JList<String>(a);
	        JScrollPane pane = new JScrollPane(list);
	        return pane;
	    }
	   
	   
	   
	public static void main(String[] args) {
		MyModelBevanda modelloBevande = new MyModelBevanda();
		MyModelSnack modelloSnack = new MyModelSnack();
		String risposta = null;
		String req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_BEVANDE;
		
		risposta = ottieniStringaDalDatabase(req);

		modelloBevande.addProdotti(risposta);
			
		listaBevande = modelloBevande.creaLista();
			
		req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_SNACK;
		
		risposta = ottieniStringaDalDatabase(req);
		
		modelloSnack.addProdotti(risposta);
			
		listaSnack = modelloSnack.creaLista();
			
		MyButtonokListener listener = new MyButtonokListener();
    	invia.addActionListener(listener);
    	MyButtonResetListener listener1 = new MyButtonResetListener();
    	reset.addActionListener(listener1);
    	
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

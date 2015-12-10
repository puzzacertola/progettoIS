package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pub.gui.BaristaGuiSetting.OrdiniSelezioneListener;
import pub.server.Server;

public class BaristaGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static String[] appo ={""};
	private static Container pane ;
	
	//creazione interfaccia
	public BaristaGui(){
		
		super("Snack in attesa");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Da fare ci sono i seguenti Snack:"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListOrdini(appo), c);
	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
		
				
	}
	   
	
	private static JScrollPane getListOrdini(String[] a){
		JList jListOrdini = new JList(a);

		JScrollPane pane = new JScrollPane(jListOrdini);

		OrdiniSelezioneListener ml = new OrdiniSelezioneListener();

		jListOrdini.addMouseListener(ml);

		return pane;

	}
	
	public static void main(String[] args) {
		
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

		//new BaristaGui();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BaristaGui();
			}
		}); 


	}
	
}
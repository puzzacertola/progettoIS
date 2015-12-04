package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pub.gui.CameriereGuiSetting.OrdiniSelezioneListener;
import pub.server.Server;

public class CassiereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Container pane ;
	private static JTextField texttavolo= new JTextField("",5);
	private static JTextArea contoTextArea = new JTextArea(20,10);
	
	//creazione interfaccia
	public CassiereGui(){
		
		super("Conto");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Tavolo: "), c);
		
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(texttavolo, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(contoTextArea, c);
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel(""), c);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
				
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
				new CassiereGui();
			}
		}); 


	}
	
}
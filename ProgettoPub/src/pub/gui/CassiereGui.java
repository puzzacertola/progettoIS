package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pub.gui.CameriereGuiSetting.MyButtonInviaListener;
import pub.gui.CameriereGuiSetting.OrdiniSelezioneListener;
import pub.gui.CassiereGuiSetting.TavoloTextFieldListener;
import pub.gui.CassiereGuiSetting.MyButtonPagatoListener;
import pub.server.Server;

/**
 * @authors Giuseppe, Giovanni
 *  
 * Interfaccia grafica del cassiere. Mostra gli ordini "Consegnati" ad un tavolo, il prezzo di ogni prodotto
 * ordinato e il conto totale.
 *
 */

public class CassiereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Container pane ;
	public static JTextField tavoloTextField = new JTextField("",5);
	public static JTextArea contoTextArea = new JTextArea(20,10);
	public static JButton pagato= new JButton("pagato");

	//creazione interfaccia
	public CassiereGui(){

		super("Conto");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Label Tavolo 
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Tavolo: "), c);

		//TextField del Tavolo 
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(tavoloTextField, c);

		//TextArea del Conto 
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(contoTextArea, c);
		contoTextArea.setEditable(false);
		
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(pagato, c);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
		MyButtonPagatoListener pagatoListener = new MyButtonPagatoListener();
		pagato.addActionListener(pagatoListener);

	}

	public static void main(String[] args) {

		TavoloTextFieldListener tavoloListener = new TavoloTextFieldListener();
		tavoloTextField.getDocument().addDocumentListener(tavoloListener);

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

		//new CassiereGui();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CassiereGui();
			}
		}); 
	}

}
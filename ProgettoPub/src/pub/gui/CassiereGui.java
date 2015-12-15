package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pub.gui.CassiereGuiSetting.TavoloTextFieldListener;
import pub.gui.CassiereGuiSetting.MyButtonPagatoListener;

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
	public static JTextArea totaleTextArea = new JTextArea(1,5);
	
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel(""), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(totaleTextArea, c);
		totaleTextArea.setEditable(false);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Totale"), c);
		
		
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
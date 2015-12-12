package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import pub.gui.OrdiniGuiSetting.StatoOrdiniSelezioneListener;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Interfaccia che mostra le ordinazioni pronte.
 *
 */

public class OrdiniGui extends JFrame{
	private static final long serialVersionUID = 1L;
	private static JTextArea tavoloTextArea = new JTextArea (2,5);
	private static Container pane;
	public static ModelloStatoOrdini modelloStatoOrdini = null;
	static JList jListOrdini = new JList();

	public OrdiniGui(String elenco){

		super("Ordinazioni Pronte");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Label Ordinazioni Pronte
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Ordinazioni pronte:"), c);
		
		//JList degli ordini

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListOrdini(elenco), c);

		//Label tavolo
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("tavolo:"), c);
		
		//TextArea tavolo (da fare)

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(tavoloTextArea, c);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);

		//quando viene inserito il numero di un tavolo filtra gli ordini effettuati per quel tavolo
		tavoloTextArea.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				//query ordina vista per tavolo
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			
			}
			
			public void keyReleased(KeyEvent arg0) {
			
			}
		});

	}
	
	//Creazione JList degli ordini
	
	private static JScrollPane getListOrdini(String elenco){
		modelloStatoOrdini = new ModelloStatoOrdini(elenco);
		
		jListOrdini = new JList(modelloStatoOrdini);

		JScrollPane pane = new JScrollPane(jListOrdini);

		StatoOrdiniSelezioneListener ml = new StatoOrdiniSelezioneListener();

		jListOrdini.addMouseListener(ml);

		return pane;

	}

}

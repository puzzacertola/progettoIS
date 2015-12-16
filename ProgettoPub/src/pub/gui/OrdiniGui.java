package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import pub.gui.OrdiniGuiSetting.StatoOrdiniSelezioneListener;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Interfaccia che mostra le ordinazioni pronte.
 *
 */

public class OrdiniGui extends JFrame{
	private static final long serialVersionUID = 1L;
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


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
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

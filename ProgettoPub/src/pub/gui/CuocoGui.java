package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pub.gui.CuocoGuiSetting.MyButtonRefreshListener;
import pub.gui.CuocoGuiSetting.OrdiniCuocoSelezioneListener;
import pub.server.Server;

/**
 * @authors Giuseppe, Giovanni
 *
 * Interfaccia grafica del cuoco. Permette di visualizzare gli ordini "Da Fare",
 * e di modificare lo stato in "Pronto".
 * 
 */

public class CuocoGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Container pane;
	public static ModelloOrdiniBarECucina modelloOrdini = null;
	static JList jListOrdini = new JList();
	private static JButton refreshBottone = new JButton("Aggiorna");	

	public CuocoGui(){

		super("Snack in attesa");
		pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Label Snack da preparare 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel("Snack da preparare:"), c);

		//JScrollPane Ordini 
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(getListOrdini(), c);

		//Bottone aggiorna
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(refreshBottone, c);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);			

		MyButtonRefreshListener refreshListener = new MyButtonRefreshListener();
		refreshBottone.addActionListener(refreshListener);
	}   

	//Crea il JScrollPane dove verrà mostrata la JList degli ordini

	private static JScrollPane getListOrdini(){
		jListOrdini = new JList(modelloOrdini);

		JScrollPane pane = new JScrollPane(jListOrdini);

		OrdiniCuocoSelezioneListener ml = new OrdiniCuocoSelezioneListener();

		jListOrdini.addMouseListener(ml);

		return pane;
	}

	public static void main(String[] args) {
		String risposta = null;
		String req = "pub:\n" + Server.SELECT_CUOCO_ORDINI;

		risposta = CuocoGuiSetting.ottieniStringaDalDatabase(req);

		modelloOrdini = new ModelloOrdiniBarECucina(risposta);

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
				new CuocoGui();
			}
		}); 
	}

}
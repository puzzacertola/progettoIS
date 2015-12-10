package pub.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pub.gui.CameriereGuiSetting;
import pub.gui.CameriereGuiSetting.BevandeSelezioneListener;
import pub.gui.CameriereGuiSetting.MyButtonInviaListener;
import pub.gui.CameriereGuiSetting.MyButtonResetListener;
import pub.gui.CameriereGuiSetting.OrdiniSelezioneListener;
import pub.gui.CameriereGuiSetting.SnackSelezioneListener;
import pub.gui.CameriereGuiSetting.StatoOrdiniButton;
import pub.server.Server;

public class CameriereGui extends JFrame{

	private static final long serialVersionUID = 1L;
	public static JTextField tavoloTextField = new JTextField("",5);
	public static JTextField idCameriereTextField = new JTextField("id",5);
	private static JButton inviaButton = new JButton("Invia");
	private static JButton resetButton = new JButton("Reset");	
	private static Container pane;
	public static MyListModelBevanda modelloBevande = null;
	public static MyListModelSnack modelloSnack = null;
	public static MyListModelOrdini modelloOrdini = null;
	public static JButton statoOrdiniButton = new JButton("Visualizza ordini");

	static JList jListBevande = new JList();
	static JList jListSnack = new JList();
	static JList jListOrdini = new JList();

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

		risposta = CameriereGuiSetting.ottieniStringaDalDatabase(req);

		modelloBevande = new MyListModelBevanda(risposta);

		req = "pub:\n" + Server.SELECT_CAMERIERE_MENU_SNACK;

		risposta = CameriereGuiSetting.ottieniStringaDalDatabase(req);

		modelloSnack = new MyListModelSnack(risposta);

		modelloOrdini = new MyListModelOrdini();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 22);
		
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));

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

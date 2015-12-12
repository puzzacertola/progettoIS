package pub.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @authors Giuseppe, Giovanni
 * 
 * Settaggi della Gui del Barista.
 *
 */

public class BaristaGuiSetting {

	public static class OrdiniSelezioneListener extends MouseAdapter{		
		@Override
		public void mouseClicked(MouseEvent evt) {
			//cambia lo stato dell'ordine a fatto
			// vedere se fare tanti click quanti sono le bevande fatte
			
			/*if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
				int index = CameriereGui.jListOrdini.locationToIndex(evt.getPoint());
				if(index >= 0){
					int n = JOptionPane.showConfirmDialog(new JFrame(),"Cancellare " 
							+ CameriereGui.modelloOrdini.getOrdinazioni().get(index).getNome() + "?"
							,"Eliminazione Ordine",JOptionPane.YES_NO_OPTION);
					if (n == 0){
						if (CameriereGui.jListOrdini.getSelectedIndex() != -1) {
							CameriereGui.modelloOrdini.deleteProdotto(index);   
							CameriereGui.jListOrdini.updateUI();
						}
					}
				}

			}*/
		}
	}
	
	
	
}

package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelConFondo extends JPanel {
	Image imagen;
	/**
	 * Create the panel.
	 */
	public PanelConFondo(Image i) {
		imagen = i;
		setLayout(new GridLayout(3, 3));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		if(imagen == null) {
			super.paintComponent(g);
		}else {
			gr.drawImage(imagen, 0, 0, this.getWidth(), this.HEIGHT, this);
		}
	}

}

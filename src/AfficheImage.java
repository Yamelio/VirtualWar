import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class AfficheImage extends JPanel {
		Image fond;

		AfficheImage(String s) {
			fond = getToolkit().getImage(s);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
		}
	}
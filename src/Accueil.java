import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accueil<JXImagePanel> extends JFrame {
	private JPanel p;
	private GridBagLayout g;
	private GridBagConstraints gc;

	public Accueil() {
		final JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		g = new GridBagLayout();
		gc = new GridBagConstraints();
		p.setLayout(g);

		Font titre = new Font("Arial", Font.PLAIN, 42);

		JLabel vw = new JLabel("VirtualWar");
		gc.gridy = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		vw.setFont(titre);
		p.add(vw, gc);

		JButton jouer = new JButton("Jouer");
		gc.gridy = 2;
		gc.weighty = 1;
		gc.ipady = 20;
		gc.fill = GridBagConstraints.HORIZONTAL;
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Parametrage();
				f.dispose();
			}
		});
		p.add(jouer, gc);

		/*
		 * JButton charger = new JButton("Charger");
		 * charger.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { new IHM();
		 * IHM.PlateauIHM.chargement("save/sauvegarde.txt"); f.dispose(); } });
		 */
		JButton credit = new JButton("Credit");
		gc.weighty = 1;
		gc.gridy = 4;
		gc.ipady = 20;
		gc.fill = GridBagConstraints.HORIZONTAL;
		credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					URI uriLien = new URI(
							"https://github.com/Yamelio/VirtualWar");
					Desktop.getDesktop().browse(uriLien);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException ex) {
					Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,
							null, ex);
				}
			}
		});

		Image image;
		JLabel viewer = null;
		try {
			image = ImageIO.read(new File("img/fond1.jpg"));
			viewer = new JLabel(new ImageIcon(image));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		p.add(credit, gc);

		f.setContentPane(new AfficheImage("img/fond1.jpg"));
		f.add(p);
		f.setVisible(true);
		f.pack();

	}

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

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Accueil();
			}
		});
	}

}

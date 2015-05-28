import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accueil extends JFrame {
	public Accueil() {
		final JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout g = new GridLayout(7, 1);
		Font titre = new Font("Arial", Font.PLAIN, 42);
		JPanel p = new JPanel();
		JPanel empty1 = new JPanel();
		JPanel empty2 = new JPanel();
		JPanel empty3 = new JPanel();
		JPanel empty4 = new JPanel();
		JPanel empty5 = new JPanel();

		p.setLayout(g);

		JLabel vw = new JLabel("VirtualWar");
		vw.setFont(titre);

		JButton jouer = new JButton("Jouer");
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Parametrage();
				f.dispose();
			}
		});

		/*
		 * JButton charger = new JButton("Charger");
		 * charger.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { new IHM();
		 * IHM.PlateauIHM.chargement("save/sauvegarde.txt"); f.dispose(); } });
		 */
		JButton credit = new JButton("Credit");
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

		p.add(vw);
		p.add(empty1);
		p.add(jouer);
		p.add(empty2);
		// p.add(charger);
		p.add(empty3);
		p.add(credit);
		f.add(empty4, BorderLayout.EAST);
		f.add(empty5, BorderLayout.WEST);
		f.getContentPane().add(p);
		f.setVisible(true);
		f.pack();

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Accueil();
			}
		});
	}

}

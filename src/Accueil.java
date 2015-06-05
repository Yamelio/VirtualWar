import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Accueil extends JFrame {

	private GridBagLayout g;
	private GridBagConstraints gc;
	public static Son musique = new Son("son/test.wav");


	public Accueil() {
		final JFrame f = new JFrame("VirtualWar");
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - f.getWidth()) / 3;
		final int y = (int) ((screenSize.height - f.getHeight()) / 4);
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(f);
		f.setLocation(x, y);
		
		g = new GridBagLayout();
		gc = new GridBagConstraints();
		AfficheImage aF = new AfficheImage("img/fond1.jpg");
		aF.setLayout(g);
		
		Font titre = new Font("Arial", Font.PLAIN, 42);

		JLabel vw = new JLabel("VirtualWar");
		gc.gridy = 0;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		vw.setFont(titre);
		aF.add(vw, gc);

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
		aF.add(jouer, gc);

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
		aF.add(credit, gc);
		
		f.getContentPane().add(aF);
		f.setVisible(true);
		f.pack();

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Accueil.musique.start();
				new Accueil();
			}
		});
	}

}

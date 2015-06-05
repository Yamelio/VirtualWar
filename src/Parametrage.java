import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Parametrage {

	private GridBagLayout g;
	private GridBagConstraints gc;
	private int largeurPlateau = 7;
	private int hauteurPlateau = 7;
	private int nbObstacle = 25;
	public Menu menu = new Menu();

	public Parametrage() {
		final JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - f.getWidth()) / 3;
		final int y = (int) ((screenSize.height - f.getHeight()) / 4);
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(f);
		f.setLocation(x, y);
		f.setJMenuBar(menu.menuBar);
		
		g = new GridBagLayout();
		gc = new GridBagConstraints();
		AfficheImage aF = new AfficheImage("img/fond1.jpg");
		aF.setLayout(g);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);

		JLabel labelParametrage = new JLabel("Paramétrage");
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.weighty = 40;
		gc.fill = GridBagConstraints.CENTER;
		gc.ipady= 10;
		Font titre = new Font("Arial", Font.PLAIN, 42);
		labelParametrage.setFont(titre);
		aF.add(labelParametrage, gc);
		
		JLabel labelHauteur = new JLabel("Hauteur du plateau:  ");
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.weighty = 0;
		aF.add(labelHauteur, gc);

		final JLabel labelValueH = new JLabel(" " + hauteurPlateau + " cases");
		gc.gridx = 3;
		gc.gridy = 1;
		gc.gridwidth = 1;
		aF.add(labelValueH, gc);
		
		final JSlider sliderHauteur = new JSlider(5, 25, 10);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		sliderHauteur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				hauteurPlateau = sliderHauteur.getValue();
				labelValueH.setText(" " + hauteurPlateau + " cases");

			}
		});
		aF.add(sliderHauteur, gc);
		
		JLabel labelLargeur = new JLabel("Largeur du plateau:  ");
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(labelLargeur, gc);
		
		final JLabel labelValueL = new JLabel(" " + largeurPlateau + " cases");
		gc.gridx = 3;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(labelValueL, gc);
		
		final JSlider sliderLargeur = new JSlider(5, 25, 10);
		sliderLargeur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				largeurPlateau = sliderLargeur.getValue();
				labelValueL.setText(" " + largeurPlateau + " cases");

			}
		});
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 2;
		aF.add(sliderLargeur, gc);
		
		JLabel labelObstacle = new JLabel("Nombre d'obstacle: ");
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(labelObstacle, gc);
		
		JLabel labelValueO = new JLabel("");
		gc.gridx = 3;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(labelValueO, gc);		
		
		final JSlider sliderObstacle = new JSlider(
				0,
				((int) sliderLargeur.getMaximum() * sliderHauteur.getMaximum()) / 2);
		sliderObstacle.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sliderObstacle.setMaximum((int) hauteurPlateau * largeurPlateau
						/ 2);
				nbObstacle = (int) ((double) sliderObstacle.getValue()
						/ sliderObstacle.getMaximum() * 100) / 2;
				labelValueO.setText(" " + nbObstacle + "%");
			}
		});
		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridwidth = 2;
		nbObstacle = 25;
		labelValueO.setText(25 + "%");	
		aF.add(sliderObstacle, gc);

		String[] modeDeJeu = { "Joueur VS Joueur", "Joueur VS IA", "IA vs IA" };
		final JComboBox<String> combo = new JComboBox<>(modeDeJeu);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 3;
		gc.weighty = 40;
		aF.add(combo, gc);

		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IHM.PlateauIHM.hauteur = hauteurPlateau;
				IHM.PlateauIHM.largeur = largeurPlateau;
				IHM.PlateauIHM.obstacles = nbObstacle;

				if (combo.getSelectedIndex() == 0) {
					IHM.PlateauIHM.nbrIA = "0";
					new CreationEquipe();
					f.dispose();
				} else if (combo.getSelectedIndex() == 1) {
					IHM.PlateauIHM.nbrIA = "1";
					new CreationEquipe();
					f.dispose();
				} else {
					IHM.PlateauIHM.nbrIA = "2";
					new IHM();
					f.dispose();
				}
			}
		});
		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridwidth = 3;
		gc.weighty = 40;
		aF.add(valider, gc);

		f.getContentPane().add(aF);
		f.setVisible(true);
		f.pack();
	}

	public int getHauteur() {
		return hauteurPlateau;
	}

	public int getLargeur() {
		return largeurPlateau;
	}

	public int getNbObstacle() {
		return nbObstacle;
	}
}

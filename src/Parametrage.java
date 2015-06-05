import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Parametrage {

	private JPanel p = new JPanel();
	private GridBagLayout g;
	private GridBagConstraints gc;
	private int largeurPlateau = 7;
	private int hauteurPlateau = 7;
	private int nbObstacle = 25;
	public Menu menu = new Menu();

	public Parametrage() {
		final JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setJMenuBar(menu.menuBar);
		
		p = new JPanel();
		g = new GridBagLayout();
		gc = new GridBagConstraints();
		p.setLayout(g);
		
		//gc.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelParametrage = new JLabel("Paramétrage");
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 4;
		
		Font titre = new Font("Arial", Font.PLAIN, 42);
		labelParametrage.setFont(titre);
		p.add(labelParametrage, gc);
		
		JLabel labelHauteur = new JLabel("Hauteur du plateau:");
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;		
		p.add(labelHauteur, gc);

		final JLabel labelValueH = new JLabel(" " + hauteurPlateau + " cases");
		gc.gridx = 3;
		gc.gridy = 1;
		gc.weightx = 1;
		p.add(labelHauteur, gc);
		
		final JSlider sliderHauteur = new JSlider(5, 25, 10);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 2;
		sliderHauteur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				hauteurPlateau = sliderHauteur.getValue();
				labelValueH.setText(" " + hauteurPlateau + " cases");

			}
		});
		p.add(sliderHauteur, gc);
		
		JLabel labelLargeur = new JLabel("Largeur du plateau:");
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		p.add(labelLargeur, gc);
		
		final JLabel labelValueL = new JLabel(" " + largeurPlateau + " cases");
		gc.gridx = 3;
		gc.gridy = 2;
		gc.weightx = 1;
		p.add(labelValueL, gc);
		
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
		gc.weightx = 2;
		p.add(sliderLargeur, gc);
		
		JLabel labelObstacle = new JLabel("Nombre d'obstacle:");
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 1;
		p.add(labelObstacle, gc);
		
		JLabel labelValueO = new JLabel("");
		gc.gridx = 3;
		gc.gridy = 3;
		gc.weightx = 1;
		p.add(labelValueO);		
		
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
		gc.weightx = 2;
		nbObstacle = 25;
		labelValueO.setText(25 + "%");	
		p.add(sliderObstacle, gc);

		String[] modeDeJeu = { "Joueur VS Joueur", "Joueur VS IA", "IA vs IA" };
		final JComboBox<String> combo = new JComboBox<>(modeDeJeu);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 4;
		p.add(combo, gc);

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
		gc.weightx = 4;
		p.add(valider, gc);

		f.getContentPane().add(p);
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

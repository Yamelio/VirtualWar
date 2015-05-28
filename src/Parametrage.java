import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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

	private JPanel panelReglage = new JPanel();
	private JPanel panelPrincipale = new JPanel();
	private GridBagConstraints gridConstraint = new GridBagConstraints();
	private int largeurPlateau = 10;
	private int hauteurPlateau = 10;
	private int nbObstacle = 0;
	public Menu menu = new Menu();

	public Parametrage() {
		JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setJMenuBar(menu.menuBar);

		panelPrincipale.setLayout(new GridLayout(3, 1));
		panelReglage.setLayout(new GridLayout(3, 3));

		JLabel labelParametrage = new JLabel("Paramétrage");
		Font titre = new Font("Arial", Font.PLAIN, 42);
		labelParametrage.setFont(titre);

		JLabel labelHauteur = new JLabel("Hauteur du plateau:");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;

		JLabel labelValueH = new JLabel(" " + hauteurPlateau + " cases");
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 1;
		JSlider sliderHauteur = new JSlider(5, 25, 10);
		sliderHauteur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				hauteurPlateau = sliderHauteur.getValue();
				labelValueH.setText(" " + hauteurPlateau + " cases");

			}
		});
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 1;
		JLabel labelLargeur = new JLabel("Largeur du plateau:");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 2;
		JLabel labelValueL = new JLabel(" " + largeurPlateau + " cases");
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 2;
		JSlider sliderLargeur = new JSlider(5, 25, 10);
		sliderLargeur.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				largeurPlateau = sliderLargeur.getValue();
				labelValueL.setText(" " + largeurPlateau + " cases");

			}
		});
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 2;
		JLabel labelObstacle = new JLabel("Nombre d'obstacle:");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 3;
		JLabel labelValueO = new JLabel(""
				+ (nbObstacle / (largeurPlateau * hauteurPlateau) * 100) + "%");
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 3;
		JSlider sliderObstacle = new JSlider(
				0,
				((int) sliderLargeur.getMaximum() * sliderHauteur.getMaximum()) / 2);
		sliderObstacle.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sliderObstacle.setMaximum((int) hauteurPlateau * largeurPlateau
						/ 2);
				nbObstacle = sliderObstacle.getValue();
				labelValueO.setText(" "
						+ (int) ((double) nbObstacle
								/ sliderObstacle.getMaximum() * 100) / 2 + "%");
			}
		});
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 3;

		String[] modeDeJeu = { "Joueur VS Joueur", "Joueur VS IA", "IA vs IA" };
		JComboBox<String> combo = new JComboBox<>(modeDeJeu);

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

		gridConstraint = new GridBagConstraints();
		gridConstraint.anchor = GridBagConstraints.LINE_END;
		gridConstraint.ipadx = 50;
		gridConstraint.ipady = 50;
		panelReglage.add(labelHauteur, gridConstraint);
		panelReglage.add(sliderHauteur, gridConstraint);
		panelReglage.add(labelValueH, gridConstraint);
		panelReglage.add(labelLargeur, gridConstraint);
		panelReglage.add(sliderLargeur, gridConstraint);
		panelReglage.add(labelValueL, gridConstraint);
		panelReglage.add(labelObstacle, gridConstraint);
		panelReglage.add(sliderObstacle, gridConstraint);
		panelReglage.add(labelValueO, gridConstraint);

		panelPrincipale.add(labelParametrage);
		panelPrincipale.add(combo);
		panelPrincipale.add(panelReglage);
		panelPrincipale.add(valider, BorderLayout.PAGE_END);
		f.getContentPane().add(panelPrincipale);
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

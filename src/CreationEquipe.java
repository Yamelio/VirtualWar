import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CreationEquipe {

	public Menu menu = new Menu();

	public CreationEquipe() {
		final JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setJMenuBar(menu.menuBar);
		Font titre = new Font("Arial", Font.PLAIN, 42);

		JPanel panelPrincipal = new JPanel();
		JPanel panelParametrage = new JPanel();
		JPanel panelEquipe1 = new JPanel();
		panelEquipe1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 80));
		JPanel panelEquipe2 = new JPanel();
		panelEquipe2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 80));

		GridLayout g1 = new GridLayout(1, 2);
		GridLayout g2 = new GridLayout(4, 3);

		JPanel vide = new JPanel();
		JPanel vide2 = new JPanel();
		JLabel equipe1 = new JLabel("Equipe Bleue: ");
		panelEquipe1.add(equipe1);
		panelEquipe1.add(vide);

		JLabel equipe2 = new JLabel("Equipe Rouge: ");
		panelEquipe2.add(equipe2);
		panelEquipe2.add(vide2);

		panelEquipe1.setLayout(g2);
		panelEquipe2.setLayout(g2);

		JLabel piegeurs1 = new JLabel("Pi�geur(s): ");
		JLabel tireurs1 = new JLabel("Tireur(s): ");
		JLabel chars1 = new JLabel("Char(s): ");

		final JTextField piegeurE1 = new JTextField("1");
		final JTextField tireurE1 = new JTextField("1");
		final JTextField charE1 = new JTextField("1");
		panelEquipe1.add(piegeurs1);
		panelEquipe1.add(piegeurE1);
		panelEquipe1.add(tireurs1);
		panelEquipe1.add(tireurE1);
		panelEquipe1.add(chars1);
		panelEquipe1.add(charE1);

		final JTextField piegeurE2 = new JTextField("1");
		final JTextField tireurE2 = new JTextField("1");
		final JTextField charE2 = new JTextField("1");

		if (!IHM.PlateauIHM.nbrIA.equals("1")) {
			JLabel piegeurs2 = new JLabel("Pi�geur(s): ");
			JLabel tireurs2 = new JLabel("Tireur(s): ");
			JLabel chars2 = new JLabel("Char(s): ");

			panelEquipe2.add(piegeurs2);
			panelEquipe2.add(piegeurE2);
			panelEquipe2.add(tireurs2);
			panelEquipe2.add(tireurE2);
			panelEquipe2.add(chars2);
			panelEquipe2.add(charE2);
			panelParametrage.add(panelEquipe2);

		}

		panelParametrage.setLayout(g1);
		panelParametrage.add(panelEquipe1);

		JLabel labelCreationEquipe = new JLabel("Cr�ation de l'�quipe");

		labelCreationEquipe.setFont(titre);

		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IHM.PlateauIHM.nbCharJ1 = Integer.parseInt(charE1.getText());
				IHM.PlateauIHM.nbTireurJ1 = Integer.parseInt(tireurE1.getText());
				IHM.PlateauIHM.nbPiegeurJ1 = Integer.parseInt(piegeurE1
						.getText());

				if (!IHM.PlateauIHM.nbrIA.equals("1")) {
					IHM.PlateauIHM.nbCharJ2 = Integer.parseInt(charE2.getText());
					IHM.PlateauIHM.nbTireurJ2 = Integer.parseInt(tireurE2
							.getText());
					IHM.PlateauIHM.nbPiegeurJ2 = Integer.parseInt(piegeurE2
							.getText());
				}
				new IHM();
				f.dispose();
			}
		});

		panelPrincipal.add(labelCreationEquipe, BorderLayout.PAGE_START);
		panelPrincipal.add(panelParametrage, BorderLayout.CENTER);
		panelPrincipal.add(valider, BorderLayout.SOUTH);
		f.getContentPane().add(panelPrincipal);
		f.setVisible(true);
		f.pack();

	}
}
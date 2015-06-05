import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - f.getWidth()) / 3;
		final int y = (int) ((screenSize.height - f.getHeight()) / 4);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(f);
		f.setLocation(x, y);
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

		JLabel piegeurs1 = new JLabel("Piegeur(s): ");
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
			JLabel piegeurs2 = new JLabel("Piegeur(s): ");
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

		JLabel labelCreationEquipe = new JLabel("Création de l'équipe");

		labelCreationEquipe.setFont(titre);

		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					IHM.PlateauIHM.nbCharJ1 = Integer.parseInt(charE2.getText()) ;
					IHM.PlateauIHM.nbTireurJ1 = Integer.parseInt(tireurE1.getText());
					IHM.PlateauIHM.nbPiegeurJ1 = Integer.parseInt(piegeurE1
							.getText());
					controleSaisie(IHM.PlateauIHM.nbCharJ1);
					controleSaisie(IHM.PlateauIHM.nbTireurJ1);
					controleSaisie(IHM.PlateauIHM.nbPiegeurJ1);
				}catch(NumberFormatException e1){
					(new Erreur()).getT01(f);
					IHM.PlateauIHM.nbCharJ1 = 1;
					IHM.PlateauIHM.nbTireurJ1 = 1;
					IHM.PlateauIHM.nbPiegeurJ1 = 1;
				}catch (Erreur e2) {
					(new Erreur()).getT02(f);
					IHM.PlateauIHM.nbCharJ2 = 1;
					IHM.PlateauIHM.nbTireurJ2 = 1;
					IHM.PlateauIHM.nbPiegeurJ2 = 1;
				}
					
				if (!IHM.PlateauIHM.nbrIA.equals("1")) {
					try{
						IHM.PlateauIHM.nbCharJ2 = Integer.parseInt(charE2.getText());
						IHM.PlateauIHM.nbTireurJ2 = Integer.parseInt(tireurE2
								.getText());
						IHM.PlateauIHM.nbPiegeurJ2 = Integer.parseInt(piegeurE2
								.getText());
						controleSaisie(IHM.PlateauIHM.nbCharJ2);
						controleSaisie(IHM.PlateauIHM.nbTireurJ2);
						controleSaisie(IHM.PlateauIHM.nbPiegeurJ2);
					}catch(NumberFormatException e3){
						(new Erreur()).getT01(f);
						IHM.PlateauIHM.nbCharJ2 = 1;
						IHM.PlateauIHM.nbTireurJ2 = 1;
						IHM.PlateauIHM.nbPiegeurJ2 = 1;
					}catch (Erreur e4) {
						(new Erreur()).getT02(f);
						IHM.PlateauIHM.nbCharJ2 = 1;
						IHM.PlateauIHM.nbTireurJ2 = 1;
						IHM.PlateauIHM.nbPiegeurJ2 = 1;
					}
					
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
	
	public static void controleSaisie(int i) throws Erreur{
		if(i < 0 ||  i > 3){
			throw new Erreur("La valeur rentrée n'est pas comprise entre 1 et 3");
		}
	}
}

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private GridBagLayout g;
	private GridBagConstraints gc;

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
			
		g = new GridBagLayout();
		gc = new GridBagConstraints();
		AfficheImage aF = new AfficheImage("img/fond1.jpg");
		aF.setLayout(g);
		
		gc.fill = GridBagConstraints.CENTER;

		JLabel labelCreationEquipe = new JLabel("Création de l'équipe");
		labelCreationEquipe.setFont(titre);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.weighty = 30;
		aF.add(labelCreationEquipe, gc);
		
		gc.weighty = 5;
		JLabel equipe1 = new JLabel("Equipe Bleue: ");
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 2;
		aF.add(equipe1, gc);
		JLabel equipe2 = new JLabel("Equipe Rouge: ");
		gc.gridx = 3;
		gc.gridy = 1;
		gc.gridwidth = 2;
		aF.add(equipe2, gc);

		JLabel piegeurs1 = new JLabel("Piegeur(s): ");
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(piegeurs1, gc);
		JLabel tireurs1 = new JLabel("Tireur(s): ");
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(tireurs1, gc);
		JLabel chars1 = new JLabel("Char(s): ");
		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 1;
		aF.add(chars1, gc);
		JLabel piegeurs2 = new JLabel("Piegeur(s): ");
		gc.gridx = 3;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(piegeurs2, gc);
		JLabel tireurs2 = new JLabel("Tireur(s): ");
		gc.gridx = 3;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(tireurs2, gc);
		JLabel chars2 = new JLabel("Char(s): ");
		gc.gridx = 3;
		gc.gridy = 4;
		gc.gridwidth = 1;
		aF.add(chars2, gc);

		JTextField piegeurE1 = new JTextField("1");
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(piegeurE1, gc);
		JTextField tireurE1 = new JTextField("1");
		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(tireurE1, gc);
		JTextField charE1 = new JTextField("1");
		gc.gridx = 1;
		gc.gridy = 4;
		gc.gridwidth = 1;
		aF.add(charE1, gc);
		JTextField piegeurE2 = new JTextField("1");
		gc.gridx = 4;
		gc.gridy = 2;
		gc.gridwidth = 1;
		aF.add(piegeurE2, gc);
		JTextField tireurE2 = new JTextField("1");
		gc.gridx = 4;
		gc.gridy = 3;
		gc.gridwidth = 1;
		aF.add(tireurE2, gc);
		JTextField charE2 = new JTextField("1");
		gc.gridx = 4;
		gc.gridy = 4;
		gc.gridwidth = 1;
		aF.add(charE2, gc);
			
		
		if (!IHM.PlateauIHM.nbrIA.equals("1")) {
			piegeurs2.setVisible(false);
			piegeurE2.setVisible(false);
			tireurs2.setVisible(false);
			tireurE2.setVisible(false);
			chars2.setVisible(false);
			charE2.setVisible(false);
		}

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
		gc.gridx = 2;
		gc.gridy = 5;
		gc.weighty = 30;
		gc.gridwidth = 1;
		aF.add(valider, gc);

		f.getContentPane().add(aF);
		f.setVisible(true);
		f.pack();

	}
	
	public static void controleSaisie(int i) throws Erreur{
		if(i < 0 ||  i > 3){
			throw new Erreur("La valeur rentrée n'est pas comprise entre 1 et 3");
		}
	}
}

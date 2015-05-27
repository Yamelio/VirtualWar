import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

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
		JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(500, 300));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setJMenuBar(menu.menuBar);
		Font titre = new Font("Arial", Font.PLAIN, 42);
		
		JPanel panelPrincipal = new JPanel();
		JPanel panelParametrage = new JPanel();
		JPanel panelEquipe1 = new JPanel();
		panelEquipe1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,80));
		JPanel panelEquipe2 = new JPanel();
		panelEquipe2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 80));
		
		GridLayout g1 = new GridLayout(1,2);
		GridLayout g2 = new GridLayout(4,3);
		
		JPanel vide = new JPanel();
		JPanel vide2 = new JPanel();
		JLabel equipe1 = new JLabel("Equipe rouge: ");
		panelEquipe1.add(equipe1);
		panelEquipe1.add(vide);
		
		JLabel equipe2 = new JLabel("Equipe bleu: ");
		panelEquipe2.add(equipe2);
		panelEquipe2.add(vide2);
		
		panelEquipe1.setLayout(g2);
		panelEquipe2.setLayout(g2);
		
		JLabel piegeurs1 = new JLabel("Piégeur(s): ");
		JLabel tireurs1 = new JLabel("Tireur(s): ");
		JLabel chars1 = new JLabel("Char(s): ");
		
		JTextField piegeurE1 = new JTextField("1");
		JTextField tireurE1 = new JTextField("1");
		JTextField charE1 = new JTextField("1");
		panelEquipe1.add(piegeurs1);panelEquipe1.add(piegeurE1);
		panelEquipe1.add(tireurs1);panelEquipe1.add(tireurE1);
		panelEquipe1.add(chars1);panelEquipe1.add(charE1);
		
		JLabel piegeurs2 = new JLabel("Piégeur(s): ");
		JLabel tireurs2 = new JLabel("Tireur(s): ");
		JLabel chars2 = new JLabel("Char(s): ");
		JTextField piegeurE2 = new JTextField("1");
		JTextField tireurE2 = new JTextField("1");
		JTextField charE2 = new JTextField("1");
		panelEquipe2.add(piegeurs2);panelEquipe2.add(piegeurE2);
		panelEquipe2.add(tireurs2);panelEquipe2.add(tireurE2);
		panelEquipe2.add(chars2);panelEquipe2.add(charE2);
		
		panelParametrage.setLayout(g1);
		panelParametrage.add(panelEquipe1);
		panelParametrage.add(panelEquipe2);
		
		JLabel labelCreationEquipe = new JLabel("Création de l'équipe");
		
		labelCreationEquipe.setFont(titre);
		
		JButton valider = new JButton("Valider");
		
		panelPrincipal.add(labelCreationEquipe, BorderLayout.PAGE_START);
		panelPrincipal.add(panelParametrage, BorderLayout.CENTER);
		panelPrincipal.add(valider, BorderLayout.SOUTH);
		f.getContentPane().add(panelPrincipal);
		f.setVisible(true);
		f.pack();
		
	}
}

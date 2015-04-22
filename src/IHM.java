package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Ihm {
	
	protected int hauteurFenetre = 600;
	protected int largeurFenetre = 800;
		
	
	public Ihm() {
		JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(largeurFenetre, hauteurFenetre));

		JPanel panelPrincipale = new JPanel();
		
		JScrollPane panelPlateau = new JScrollPane();
		panelPlateau.setBorder(BorderFactory.createTitledBorder("Plateau de Jeu"));
		JPanel panelInformation = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelPlateau,panelInformation); 
		
		JPanel panelRobot = new JPanel();	
		panelRobot.setBorder(BorderFactory.createTitledBorder("Robot"));
		JPanel panelAction = new JPanel();
		panelAction.setBorder(BorderFactory.createTitledBorder("Action"));
		JScrollPane panelHistorique = new JScrollPane();
		panelHistorique.setBorder(BorderFactory.createTitledBorder("Historique"));

		panelPrincipale.setLayout(new GridLayout(1, 2));
		panelInformation.setLayout(new GridLayout(3, 1));
		splitPane.setDividerLocation((int)  largeurFenetre * 70 / 100); 

		f.getContentPane().add(panelPrincipale);
		panelPrincipale.add(splitPane);
		panelInformation.add(panelRobot, BorderLayout.NORTH);
		panelInformation.add(panelAction, BorderLayout.CENTER);
		panelInformation.add(panelHistorique, BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Ihm();
			}
		});
	}
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

public class IHM {

	protected int hauteurFenetre = 700;
	protected int largeurFenetre = 900;
	

	public IHM() {
		JFrame f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(largeurFenetre, hauteurFenetre));
		f.setMinimumSize(new Dimension(500, 400));
		f.setJMenuBar(MenuBar());
		
		//Panel principale
		JPanel panelPrincipale = new JPanel();
		
		//Panel de gauche
		JScrollPane panelPlateau = new JScrollPane();
		panelPlateau.setBorder(BorderFactory
				.createTitledBorder("Plateau de Jeu"));
		
		//Panel de droite
		JPanel panelInformation = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelPlateau, panelInformation);
		
		//Panel d'information sur le robot
		JPanel panelRobot = new JPanel();
		panelRobot.setBorder(BorderFactory.createTitledBorder("Robot"));
		JProgressBar barDeVie = new JProgressBar();
		barDeVie.setMaximum(10); //mettre ici la constante de vie max du robot
		barDeVie.setMinimum(0);
		barDeVie.setBorderPainted(true);
		barDeVie.setStringPainted(true);
		barDeVie.setString("5/10"); // nombre de pv du robot / pv max du robot
		barDeVie.setValue(5); //nombre de pv du robot
		panelRobot.setLayout(new GridLayout(2,1));
		panelRobot.add(barDeVie, BorderLayout.NORTH);
		//panelRobot.add(null, BorderLayout.SOUTH);
		
		//Panel du choix d'action
		JPanel panelAction = new JPanel();
		panelAction.setBorder(BorderFactory.createTitledBorder("Action"));
		JButton bouttonAttaquer = new JButton("Attaquer");
		JButton bouttonMouvoir = new JButton("Mouvoir");
		bouttonAttaquer.setPreferredSize(new Dimension(largeurFenetre / 9, hauteurFenetre / 10));
		bouttonMouvoir.setPreferredSize(new Dimension(largeurFenetre / 9, hauteurFenetre / 10));
		panelAction.add(bouttonAttaquer);
		panelAction.add(bouttonMouvoir);
		
		//Panel historique
		JTextArea display = new JTextArea();
		display.setEditable(false);
		JScrollPane panelHistorique = new JScrollPane(display);
		panelHistorique.setBorder(BorderFactory
				.createTitledBorder("Historique"));
		panelHistorique
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//Le bordel pour tout disposer
		panelPrincipale.setLayout(new GridLayout(1, 2));
		panelInformation.setLayout(new GridLayout(3, 1));
		splitPane.setDividerLocation((int) largeurFenetre * 70 / 100);
		f.getContentPane().add(panelPrincipale);
		panelPrincipale.add(splitPane);
		panelInformation.add(panelRobot, BorderLayout.NORTH);
		panelInformation.add(panelAction, BorderLayout.CENTER);
		panelInformation.add(panelHistorique, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}

	public JMenuBar MenuBar() {
		// Build the menu bar
		JMenuBar menuBar = new JMenuBar();

		// Build new tab "Menu"
		JMenu Menu = new JMenu("Menu");
		Menu.getAccessibleContext().setAccessibleDescription(
				"The principal menu in this program");
		menuBar.add(Menu);

		// Build new tab "Help"
		JMenu Help = new JMenu("Aide");
		Menu.getAccessibleContext().setAccessibleDescription(
				"The help menu in this program");
		menuBar.add(Help);

		// Build new tab "About us"
		JMenu AboutUs = new JMenu("A propos de nous");
		Menu.getAccessibleContext().setAccessibleDescription(
				"The link to developper page");
		menuBar.add(AboutUs);

		// Build the item "Nouveau" in "menu"
		JMenuItem menuNouveau = new JMenuItem("Nouveau");
		menuNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		menuNouveau.getAccessibleContext().setAccessibleDescription(
				"Open a new windows");
		Menu.add(menuNouveau);
		menuNouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IHM();
			}
		});

		// Build the item "Ouvrir" in "menu"
		JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
		menuOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		menuOuvrir.getAccessibleContext().setAccessibleDescription(
				"Open a pevious game");
		Menu.add(menuOuvrir);
		menuOuvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// code pour ouvrir un fichier
			}
		});

		// Build the item "Save" in "menu"
		JMenuItem menuSave = new JMenuItem("Sauvegarder");
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		menuSave.getAccessibleContext().setAccessibleDescription(
				"Open current game");
		Menu.add(menuSave);
		menuSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// code pour sauvegarder un fichier
			}
		});

		// Build the item "SaveAs" in "menu"
		JMenuItem menuSaveAs = new JMenuItem("Sauvegarder sous");
		menuSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_DOWN_MASK));
		menuSave.getAccessibleContext().setAccessibleDescription(
				"Open current game as");
		Menu.add(menuSaveAs);
		menuSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// code pour sauvegarder sous un fichier
			}
		});

		// Build the item "Exit" in "menu"
		Menu.addSeparator();
		JMenuItem menuExit = new JMenuItem("Quitter");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_DOWN_MASK));
		menuExit.getAccessibleContext().setAccessibleDescription(
				"Ce bouton quitte le programme");
		Menu.add(menuExit);
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		return menuBar;
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new IHM();
			}
		});
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IHM {

	protected int hauteurFenetre = 700;
	protected int largeurFenetre = 900;

	private File currentFile = null;
	private JTextArea historique;
	private JFrame f;

	public IHM() {
		f = new JFrame("VirtualWar");
		f.setPreferredSize(new Dimension(largeurFenetre, hauteurFenetre));
		f.setMinimumSize(new Dimension(500, 400));
		f.setJMenuBar(MenuBar());

		// Panel principale
		JPanel panelPrincipale = new JPanel();

		// Panel de gauche
		JScrollPane panelPlateau = new JScrollPane();
		panelPlateau.setBorder(BorderFactory
				.createTitledBorder("Plateau de Jeu"));
		//Ajout du truc du chef de projet
		panelPlateau.add(TestIHM.start());

		// Panel de droite
		JPanel panelInformation = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelPlateau, panelInformation);

		// Panel d'information sur le robot
		JPanel panelRobot = new JPanel();
		panelRobot.setBorder(BorderFactory.createTitledBorder("Robot"));
		GridLayout gridLayoutRobot = new GridLayout(2, 1);
		panelRobot.setLayout(gridLayoutRobot);
		gridLayoutRobot.setVgap(10);

		JProgressBar barDeVie = new JProgressBar();
		barDeVie.setForeground(Color.GREEN);
		barDeVie.setBackground(Color.RED);
		barDeVie.setMaximum(10); // mettre ici la constante de vie max du robot
		barDeVie.setMinimum(0);
		barDeVie.setBorderPainted(true);
		barDeVie.setStringPainted(true);
		barDeVie.setString("5/10"); // nombre de pv du robot / pv max du robot
		barDeVie.setValue(5); // nombre de pv du robot

		JLabel test = new JLabel("Hello");
		
		// BufferedImage imageRobot = ImageIO.read(new File("robot.jpg"));

		panelRobot.add(barDeVie, BorderLayout.NORTH);
		panelRobot.add(test, BorderLayout.CENTER);

		// Panel du choix d'action
		JPanel panelAction = new JPanel();
		panelAction.setBorder(BorderFactory.createTitledBorder("Action"));
		JButton bouttonAttaquer = new JButton("Attaquer");
		JButton bouttonMouvoir = new JButton("Mouvoir");
		bouttonAttaquer.setPreferredSize(new Dimension(largeurFenetre / 9,
				hauteurFenetre / 10));
		bouttonMouvoir.setPreferredSize(new Dimension(largeurFenetre / 9,
				hauteurFenetre / 10));
		panelAction.add(bouttonAttaquer);
		panelAction.add(bouttonMouvoir);

		// Panel historique
		historique = new JTextArea();
		historique.setEditable(false);
		JScrollPane panelHistorique = new JScrollPane(historique);
		panelHistorique.setBorder(BorderFactory
				.createTitledBorder("Historique"));
		panelHistorique
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Le bordel pour tout disposer
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
				JFileChooser fc = new JFileChooser();
				File file = fc.getSelectedFile();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier de sauvegarde VirtualWar (.txt)", "txt",
						"text");
				fc.setFileFilter(filter);
				File workingDirectory = new File(System.getProperty("user.dir")
						+ "/save");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showOpenDialog(menuOuvrir);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ fc.getSelectedFile().getName());
				}
				currentFile = file;
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
				if (currentFile == null) {
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					File workingDirectory = new File(System
							.getProperty("user.dir") + "/save");
					fc.setCurrentDirectory(workingDirectory);
					int returnVal = fc.showSaveDialog(menuSave);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						// on ecrit dans le fichier comme pour un save as
						FileWriter writer = null;
						try {
							writer = new FileWriter(fc.getSelectedFile());
							writer.write(historique.getText());
						} catch (Exception e3) {
							JOptionPane.showMessageDialog(null,
									"Imbossible de sauvegarder le fichier",
									"Erreur", JOptionPane.ERROR_MESSAGE);
						} finally {
							try {
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} else {
					// ecriture dans le fichier
					FileWriter writer = null;
					try {
						writer = new FileWriter(currentFile);
						writer.write(historique.getText());
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null,
								"Imbossible de sauvegarder le fichier",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
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
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				File workingDirectory = new File(System.getProperty("user.dir")
						+ "/save");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showSaveDialog(menuSaveAs);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					FileWriter writer = null;
					try {
						writer = new FileWriter(fc.getSelectedFile());
						writer.write(historique.getText());
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null,
								"Imbossible de sauvegarder le fichier",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		// Build the item "plein ecran" in menu
		Menu.addSeparator();
		JMenuItem menuPleinEcran = new JMenuItem("Mode plein ecran");
		menuPleinEcran.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK));
		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		menuPleinEcran.getAccessibleContext().setAccessibleDescription(
				"Ce bouton met je jeu en plein ecran");
		Menu.add(menuPleinEcran);
		menuPleinEcran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					device.setFullScreenWindow(f);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Impossible de mettre le jeu en plein ecran",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
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
	
	public static class TestIHM extends JPanel {
		public class Case extends Polygon {
			private Position p;

			public Case(Polygon pol, Position pos) {
				super(pol.xpoints, pol.ypoints, pol.npoints);
				this.p = pos;
			}

			public void setPosition(Position p) {
				this.p = p;
			}

			public Position getPosition() {
				return p;
			}
		}

		List<Case> liste = new ArrayList<Case>();
		final Image zero = Toolkit.getDefaultToolkit().getImage("img/0.png");
		Image un = Toolkit.getDefaultToolkit().getImage("img/1.png");
		Image deux = Toolkit.getDefaultToolkit().getImage("img/2.png");
		Image trois = Toolkit.getDefaultToolkit().getImage("img/3.png");
		Case survol;
		int hauteur = 100;
		int largeur = 100;
		private static List<Action> actions = new ArrayList<Action>();
		private static Plateau p;
		private Robot robotChoisi;

		// private static List<Robot> robotsInit;

		public TestIHM() {

			super();

			for (int i = 1; i <= Position.getPlateau().getHauteur(); i++) {
				for (int k = 1; k <= Position.getPlateau().getLargeur(); k++) {
					int isoX = i * hauteur / 2 - k * largeur / 2;
					int isoY = (i * hauteur / 2 + k * largeur / 2) / 2;
					liste.add(new Case((new Polygon(new int[] { isoX + 250,
							isoX + 250 + (largeur / 2), isoX + 250 + largeur,
							isoX + 250 + (largeur / 2) }, new int[] {
							isoY + (3 * hauteur) / 4, isoY + hauteur,
							isoY + (3 * hauteur) / 4, isoY + (hauteur / 2) }, 4)),
							Position.getPlateau()
									.getCarte()
									.get(Position.getPlateau().posToString(
											new Position(i, k)))));
				}
			}
			JFrame back = new JFrame("");
			back.getContentPane().add(this);
			back.setLocation(0, 0);
			back.setSize(700, 700);
			back.setVisible(true);
			back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.addMouseMotionListener(new MouseMotionListener() {

				public void mouseDragged(MouseEvent e) {

				}

				public void mouseMoved(MouseEvent e) {
					survol = null;
					for (Case c : liste) {
						if (c.contains(e.getX(), e.getY())) {
							survol = c;
							break;

						}
					}
					repaint();
				}

			});
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					for (Case c : liste) {
						if (c.contains(e.getX(), e.getY())) {
							if (c.getPosition().estRobot()) {
								robotChoisi = c.getPosition().getRobot();
							} else {
								try {
									actions.add(new Deplacement(robotChoisi, c
											.getPosition()));
								} catch (Erreur e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					repaint();

				}
			});
			this.setVisible(true);

		}

		public void paint(Graphics g) {
			// g.clearRect(0, 0, 1000, 1000);
			super.paintComponent(g);

			g.setColor(Color.BLACK);
			for (Case c : liste) {
				Image draw = zero;
				if (c.getPosition().estBase()) {
					draw = un;
				} else {
					if (c.getPosition().estRobot()) {
						draw = trois;
					} else {
						if (survol != null) {
							if (c.xpoints.equals(survol.xpoints)
									&& c.ypoints.equals(survol.ypoints)) {
								draw = deux;
							}
						}
					}
				}
				g.drawImage(draw, c.xpoints[0], c.ypoints[1] - hauteur, hauteur,
						largeur, this);
			}
		}

		public void animer() {
			boolean fin = false;

			repaint();

			/*
			 * while (fin == false) { this.repaint(); try { Thread.sleep(100l); }
			 * catch (InterruptedException e) { e.printStackTrace(); } }
			 */
		}
			
		
		//Cette procedure remplace le main initial
		public static TestIHM start() {
			p = new Plateau(5, 5, 0);
			p.initObstacles();
			p.ajouterListeRobot(new Tireur(0));
			p.ajouterListeRobot(new Piegeur(0));
			p.ajouterListeRobot(new Char(0));
			p.ajouterListeRobot(new Tireur(1));
			p.ajouterListeRobot(new Piegeur(1));
			p.ajouterListeRobot(new Char(1));
			Position.setPlateau(p);

			try {
				actions.add(new Deplacement(p.getListeRobot().get(0), p
						.stringToPos("B2")));
				actions.add(new Deplacement(p.getListeRobot().get(4), p
						.stringToPos("D4")));
			} catch (Erreur e) {
				e.printStackTrace();
			}
			return new TestIHM();
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new IHM();
			}
		});
	}
}

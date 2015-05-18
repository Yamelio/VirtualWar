import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
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
import java.util.Random;
import java.util.Scanner;

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
	private static JTextArea historique = new JTextArea(" ");
	private JFrame f;
	static int choixAction = 0;
	public static Position choixCible;
	public static boolean joueur = false;
	public static int fin = 2;
	public static String joueurCourant; // J1 ou J2
	static JProgressBar barDeVie;
	static JLabel affJoueurCourant = new JLabel(" ");
	static JPanel panelRobot = new JPanel();
	static boolean tactique = false;

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
		// Ajout du truc du chef de projet
		panelPlateau.add(PlateauIHM.start());

		// Panel de droite
		JPanel panelInformation = new JPanel();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelPlateau, panelInformation);

		// Panel d'information sur le robot
		panelRobot.setBorder(BorderFactory.createTitledBorder("Robot"));
		GridLayout gridLayoutRobot = new GridLayout(2, 1);
		panelRobot.setLayout(gridLayoutRobot);
		gridLayoutRobot.setVgap(10);

		barDeVie = new JProgressBar();
		barDeVie.setForeground(Color.GREEN);
		barDeVie.setBackground(Color.RED);
		barDeVie.setMaximum(0);
		barDeVie.setValue(0);
		barDeVie.setString("");
		barDeVie.setBorderPainted(true);
		barDeVie.setStringPainted(true);

		affJoueurCourant = new JLabel("Joueur courant :");

		// BufferedImage imageRobot = ImageIO.read(new File("robot.jpg"));

		panelRobot.add(barDeVie, BorderLayout.NORTH);
		panelRobot.add(affJoueurCourant, BorderLayout.CENTER);

		// Panel du choix d'action
		JPanel panelAction = new JPanel();
		panelAction.setBorder(BorderFactory.createTitledBorder("Action"));
		JButton bouttonAttaquer = new JButton("Attaquer");
		bouttonAttaquer.addMouseListener(new MouseListener() {

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
			public void mouseClicked(MouseEvent arg0) {
				choixAction = 1;
			}
		});

		JButton bouttonMouvoir = new JButton("Mouvoir");
		bouttonMouvoir.addMouseListener(new MouseListener() {

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
			public void mouseClicked(MouseEvent arg0) {
				choixAction = 2;
			}
		});
		JButton boutonTactique = new JButton("Mode tactique");
		boutonTactique.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				tactique = !tactique;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		boutonTactique.setPreferredSize(new Dimension(2 * (largeurFenetre / 9),
				hauteurFenetre / 10));

		panelAction.add(bouttonAttaquer);
		panelAction.add(bouttonMouvoir);
		panelAction.add(boutonTactique);

		// Panel historique
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

	@SuppressWarnings("serial")
	public static class PlateauIHM extends JPanel {

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
		private Case survol;
		private int hauteur = 100;
		private int largeur = 100;
		private static List<Action> actions = new ArrayList<Action>();
		private static Plateau p;
		private static Robot robotChoisi = null;
		private static Robot robotCible = null;
		private static List<Robot> robotsInit;
		private static String nbrIA = "0";
		private static IntelligenceArtificielle IA1 = null;
		private static IntelligenceArtificielle IA2 = null;

		public PlateauIHM() {
			super();

			for (int i = 1; i <= Position.getPlateau().getLargeur(); i++) {
				for (int k = 1; k <= Position.getPlateau().getHauteur(); k++) {
					int isoX = i * hauteur / 2 - k * largeur / 2;
					int isoY = (i * hauteur / 2 + k * largeur / 2) / 2;
					liste.add(new Case(
							(new Polygon(new int[] { isoX + 250,
									isoX + 250 + (largeur / 2),
									isoX + 250 + largeur,
									isoX + 250 + (largeur / 2) }, new int[] {
									isoY + (3 * hauteur) / 4, isoY + hauteur,
									isoY + (3 * hauteur) / 4,
									isoY + (hauteur / 2) }, 4)), Position
									.getPlateau()
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
			this.setVisible(true);

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
					if (joueur) {
						affJoueurCourant.setText("Joueur courant : J1");
					} else {
						affJoueurCourant.setText("Joueur courant : J2");
					}
					int bouton = e.getButton();
					boolean saisieOk = false;
					for (Case c : liste) {
						if (c.contains(e.getX(), e.getY())) {
							if (bouton == 1) {
								if (c.getPosition().estRobot()) {
									robotChoisi = c.getPosition().getRobot();
									barDeVie.setMaximum(robotChoisi
											.getEnergieMax());
									barDeVie.setValue(robotChoisi.getEnergie());
									barDeVie.setString(robotChoisi.getEnergie()
											+ "/" + robotChoisi.getEnergieMax());
									panelRobot.setBorder(BorderFactory
											.createTitledBorder("Robot "
													+ robotChoisi.getId()));

								} else {
									choixCible = c.getPosition();
								}
							} else {
								if (bouton == 3) {
									robotCible = c.getPosition().getRobot();
								}
							}
							if (robotChoisi != null) {
								if ((robotChoisi.getEquipe() == 0 && joueur)
										|| (robotChoisi.getEquipe() == 1 && !joueur)) {
									if (choixAction == 1) {
										try {
											if (robotCible != null) {
												actions.add(new Attaque(
														robotChoisi, robotCible
																.getPosition()));
												if (!(robotChoisi instanceof Piegeur)) {
													robotCible = robotsInit
															.get(c.getPosition()
																	.getRobot()
																	.getId());
												}
												saisieOk = true;
											}
										} catch (Erreur err) {
											System.out.println(err.getMessage());
										}
									} else {
										if (choixAction == 2) {
											try {
												actions.add(new Deplacement(
														robotChoisi, c
																.getPosition()));
												saisieOk = true;
											} catch (Erreur err) {
											}
										}
									}
								}
							}
						}
						if (saisieOk) {
							saisieOk = false;
							if (!actions.isEmpty()) {

								if (actions.get(actions.size() - 1) instanceof Deplacement) {
									historique.setText(historique.getText()
											+ "\nLe robot "
											+ robotChoisi.getId()
											+ " s'est deplace en "
											+ p.posToString(choixCible) + "\n");
								} else {

									if (robotChoisi instanceof Piegeur) {
										historique.setText(historique.getText()
												+ "\nLe robot "
												+ robotChoisi.getId()

												+ " a pose une mine");
									} else {
										if (robotCible != null) {
											historique.setText(historique
													.getText()
													+ "\nLe robot "
													+ robotChoisi.getId()

													+ " a attaque le robot "
													+ robotCible.getId() + "\n");
										}
									}
								}
							}
							choixAction = 0;
							robotChoisi = null;
							barDeVie.setMaximum(0);
							barDeVie.setValue(0);
							barDeVie.setString("");
							joueur = !joueur;
							PlateauIHM.p.recharges();
							PlateauIHM.sauvegarde();
							repaint();
							fin = PlateauIHM.checkFin();
							switch (fin) {

							case 1:
								PlateauIHM.p.afficherRobotsJ2();
								System.out.println("Joueur 2 a gagne !");
								break;

							case 0:
								PlateauIHM.p.afficherRobotsJ1();
								System.out.println("Joueur 1 a gagne !");
								break;

							case -1:
								System.out.println("Match nul !");
								break;
							}
						}
					}
				}
			});
		}

		public void paint(Graphics g) {
			super.paintComponent(g);
			final Image base1 = Toolkit.getDefaultToolkit().getImage(
					"img/base1.png");
			final Image base2 = Toolkit.getDefaultToolkit().getImage(
					"img/base2.png");
			final Image tireur1 = Toolkit.getDefaultToolkit().getImage(
					"img/tireur1.png");
			final Image tireur2 = Toolkit.getDefaultToolkit().getImage(
					"img/tireur2.png");
			final Image char1 = Toolkit.getDefaultToolkit().getImage(
					"img/3.png");
			final Image char2 = Toolkit.getDefaultToolkit().getImage(
					"img/1.png");
			final Image piegeur1 = Toolkit.getDefaultToolkit().getImage(
					"img/piegeur1.png");
			final Image piegeur2 = Toolkit.getDefaultToolkit().getImage(
					"img/piegeur2.png");
			final Image obstacle1 = Toolkit.getDefaultToolkit().getImage(
					"img/obstacle1.png");
			final Image obstacle2 = Toolkit.getDefaultToolkit().getImage(
					"img/obstacle2.png");
			final Image fond = Toolkit.getDefaultToolkit().getImage(
					"img/fond1.jpg");
			g.drawImage(fond, 10, 10, 700, 700, this);
			g.setColor(Color.BLACK);
			for (Case c : liste) {
				if (survol != null) {
					if (c.xpoints.equals(survol.xpoints)
							&& c.ypoints.equals(survol.ypoints)) {
						g.setColor(Color.ORANGE);
						g.fillPolygon(c);
					}
				}
				if (c.getPosition().estMine()) {
					g.setColor(Color.RED);
					g.fillPolygon(c);
				}
				g.setColor(Color.BLACK);
				g.drawPolygon(c);
			}
			for (Case c : liste) {
				if (c.getPosition().estBase()) {
					if (c.getPosition().getEquipe() == 0) {
						g.drawImage(base1, c.xpoints[0],
								c.ypoints[1] - hauteur, hauteur, largeur, this);
					} else {
						g.drawImage(base2, c.xpoints[0],
								c.ypoints[1] - hauteur, hauteur, largeur, this);
					}
				} else {
					if (c.getPosition().estRobot()) {
						Robot r = c.getPosition().getRobot();
						if (r instanceof Char) {
							if (r.getEquipe() == 0) {
								g.drawImage(char1, c.xpoints[0], c.ypoints[1]
										- hauteur, hauteur, largeur, this);
							} else {
								g.drawImage(char2, c.xpoints[0], c.ypoints[1]
										- hauteur, hauteur, largeur, this);
							}
						} else if (r instanceof Piegeur) {
							if (r.getEquipe() == 0) {
								g.drawImage(piegeur1, c.xpoints[0],
										c.ypoints[1] - hauteur, hauteur,
										largeur, this);
							} else {
								g.drawImage(piegeur2, c.xpoints[0],
										c.ypoints[1] - hauteur, hauteur,
										largeur, this);
							}
						} else {
							if (r instanceof Tireur) {
								if (r.getEquipe() == 0) {
									g.drawImage(tireur1, c.xpoints[0],
											c.ypoints[1] - hauteur, hauteur,
											largeur, this);
								} else {
									g.drawImage(tireur2, c.xpoints[0],
											c.ypoints[1] - hauteur, hauteur,
											largeur, this);
								}
							}
						}

					} else if (c.getPosition().estObstacle()) {
						if (!tactique) {
							g.drawImage(obstacle1, c.xpoints[0], c.ypoints[1]
									- hauteur, hauteur, largeur, this);
						} else {
							g.setColor(Color.GRAY);
							g.fillPolygon(c);
							g.setColor(Color.BLACK);
							g.drawPolygon(c);
						}
					}
				}
			}
		}

		// Cette procedure remplace le main initial
		public static PlateauIHM start() {
			Scanner s = new Scanner(System.in);

			System.out.println("Voulez vous charger une partie ? (o/n)");
			String rep;
			do {
				rep = s.next();
			} while (!(rep.equals("o") || rep.equals("n")));

			if (rep.equals("o")) {
				chargement();

				robotChoisi = actions.get(actions.size() - 1).getRobot();
				choixCible = actions.get(actions.size() - 1).getCible();

			} else {

				do {
					System.out
							.println("Combien de joueur virtuels voulez vous ?(0 à 2)");
					nbrIA = s.next();
				} while (!(nbrIA.equals("0") || nbrIA.equals("1") || nbrIA
						.equals("2")));

				int nbTireurJ1;
				int nbPiegeurJ1;
				int nbCharJ1;
				if (nbrIA.equals("0") || nbrIA.equals("1")) {

					nbTireurJ1 = 10;
					nbPiegeurJ1 = 10;
					nbCharJ1 = 10;
					try {
						do {
							System.out
									.println("Joueur 1, choisissez vos robots(Maximum 5) :\n\tTireur : ");
							nbTireurJ1 = s.nextInt();
							System.out.println("\n\tChar : ");
							nbCharJ1 = s.nextInt();
							System.out.println("\n\tPiegeur : ");
							nbPiegeurJ1 = s.nextInt();

						} while (nbTireurJ1 + nbCharJ1 + nbPiegeurJ1 > 5
								|| nbTireurJ1 + nbCharJ1 + nbPiegeurJ1 <= 0);

					} catch (Exception e) {
						System.out.println("Erreur de saisie");
						s.next();
					}

				} else {
					nbTireurJ1 = 0;
					nbPiegeurJ1 = 0;
					nbCharJ1 = 0;
				}

				int nbTireurJ2;
				int nbCharJ2;
				int nbPiegeurJ2;
				if (nbrIA.equals("0")) {

					nbTireurJ2 = 10;
					nbCharJ2 = 10;
					nbPiegeurJ2 = 10;

					try {
						do {
							System.out
									.println("\n\nJoueur 2, choisissez vos robots(Maximum 5) :\n\tTireur : ");
							nbTireurJ2 = s.nextInt();
							System.out.println("\n\tChar : ");
							nbCharJ2 = s.nextInt();
							System.out.println("\n\tPiegeur : ");
							nbPiegeurJ2 = s.nextInt();

						} while (nbTireurJ2 + nbCharJ2 + nbPiegeurJ2 > 5
								|| nbTireurJ2 + nbCharJ2 + nbPiegeurJ2 <= 0);
					} catch (Exception e) {
						System.out.println("Erreur de saisie");
						s.next();
					}
				} else {
					nbTireurJ2 = 0;
					nbCharJ2 = 0;
					nbPiegeurJ2 = 0;
				}

				int largeur = 0;
				int hauteur = 0;

				do {
					try {
						System.out
								.println(" Entrez la taille de la map (largeur, puis hauteur) entre 5 et 26");
						largeur = s.nextInt();
						hauteur = s.nextInt();
					} catch (Exception e) {
						System.out.println("Erreur de saisie");
						s.next();
					}
				} while (largeur < 5 || largeur > 26 || hauteur < 5
						|| hauteur > 26);
				int obstacles = -1;
				do {
					try {
						System.out
								.println("\n\nChoisissez un pourcentage d'obstacles (entier entre 0 et 50)");
						obstacles = s.nextInt();
					} catch (Exception e) {
						System.out.println("Erreur de saisie");
						s.next();
					}
				} while (!(obstacles > -1 && obstacles < 51));

				p = new Plateau(largeur, hauteur, obstacles);
				p.initObstacles();

				for (int i = 0; i < nbTireurJ1; i++) {
					p.ajouterListeRobot(new Tireur(0));
				}
				for (int i = 0; i < nbCharJ1; i++) {
					p.ajouterListeRobot(new Char(0));
				}
				for (int i = 0; i < nbPiegeurJ1; i++) {
					p.ajouterListeRobot(new Piegeur(0));
				}
				for (int i = 0; i < nbTireurJ2; i++) {
					p.ajouterListeRobot(new Tireur(1));
				}
				for (int i = 0; i < nbCharJ2; i++) {
					p.ajouterListeRobot(new Char(1));
				}
				for (int i = 0; i < nbPiegeurJ2; i++) {
					p.ajouterListeRobot(new Piegeur(1));
				}

				initIA(nbrIA);

			}
			robotsInit = new ArrayList<Robot>(p.getListeRobot());
			return new PlateauIHM();
		}

		/**
		 * Verifie si la partie est finie
		 * 
		 * @return int Entier correspondant a la situation
		 */
		private static int checkFin() {
			int aliveJ1 = 0;
			int aliveJ2 = 0;
			List<Robot> toRemove = new ArrayList<Robot>();
			for (Robot r : Position.getPlateau().getListeRobot()) {
				if (r.getEnergie() > 0) {
					if (isNotLegume(r)) {
						if (r.getEquipe() == 0) {
							aliveJ1++;
						} else {
							aliveJ2++;
						}
					}
				} else {
					toRemove.add(r);
				}
			}

			for (Robot r : toRemove) {
				Position.getPlateau().getListeRobot().remove(r);
			}

			if (aliveJ1 == 0 && aliveJ2 == 0) {
				return -1;
			}

			if (aliveJ1 == 0) {
				return 1;
			}
			if (aliveJ2 == 0) {
				return 0;
			}
			return 2;
		}

		/**
		 * Verifie que le robot en parametre peut faire quelque chose
		 * 
		 * @return boolean true si peut agir, false si non
		 */
		private static boolean isNotLegume(Robot r) {
			if (deplacementPossible(r) || robotAPortee(r)) {
				if (r instanceof Tireur) {
					return (r.getEnergie() >= Constantes
							.getCoutDeplacementTireur() || r.getEnergie() >= Constantes
							.getCoutTirTireur());
				}
				if (r instanceof Char) {
					return r.getEnergie() >= Constantes
							.getCoutDeplacementChar()
							|| r.getEnergie() >= Constantes.getCoutTirChar();
				}
				if (r instanceof Piegeur) {
					return r.getEnergie() >= Constantes
							.getCoutDeplacementPiegeur()
							|| r.getEnergie() >= Constantes.getCoutMine();
				}

			}

			return false;
		}

		/**
		 * Verifie si le robot a un autre robot en vue
		 * 
		 * @return boolean true si au moins un robot en vue
		 */
		public static boolean robotAPortee(Robot robot) {
			int portee;
			List<Robot> robotsPortee = new ArrayList<Robot>();

			if (robot instanceof Tireur) {
				portee = Constantes.getPorteeTireur();
			}

			else if (robot instanceof Char) {
				portee = Constantes.getPorteeChar();
			}

			else {
				return false;
			}

			int PositionX = robot.getPosition().getX();
			int PositionY = robot.getPosition().getY();
			String strTemp;
			Position posTemp;

			for (int i = -portee; i <= portee; i++) {
				posTemp = new Position(PositionX + i, PositionY);
				strTemp = p.posToString(posTemp);
				posTemp = p.getCarte().get(strTemp);

				if (posTemp != null && posTemp.estRobot()
						&& champDegage(robot, posTemp.getRobot())) {
					robotsPortee.add(posTemp.getRobot());
				}

				posTemp = new Position(PositionX, PositionY + i);
				strTemp = p.posToString(posTemp);
				posTemp = p.getCarte().get(strTemp);

				if (posTemp != null && posTemp.estRobot()
						&& champDegage(robot, posTemp.getRobot())) {
					return true;
				}
			}

			return robotsPortee.size() == 0;
		}

		/**
		 * Verifie si le robot a le champ libre pour tirer sur un autre robot
		 * 
		 * @return boolean true si le champ est libre
		 */
		public static boolean champDegage(Robot r, Robot cible) {
			Position posTemp;
			String strTemp;
			if (r.getPosition().getX() == cible.getPosition().getX()) {
				for (int i = 1; i < Math.abs(r.getPosition().getY()
						- cible.getPosition().getY()); i++) {
					if (r.getPosition().getY() < cible.getPosition().getY()) {
						posTemp = new Position(r.getPosition().getX(), r
								.getPosition().getY() + i);
					} else {
						posTemp = new Position(r.getPosition().getX(), cible
								.getPosition().getY() + i);
					}
					strTemp = p.posToString(posTemp);
					posTemp = p.getCarte().get(strTemp);
					if (posTemp.estObstacle() || posTemp.estRobot()) {
						return false;
					}
				}
				return true;
			}

			else if (r.getPosition().getY() == cible.getPosition().getY()) {
				for (int i = 1; i < Math.abs(r.getPosition().getX()
						- cible.getPosition().getX()); i++) {
					if (r.getPosition().getX() < cible.getPosition().getX()) {
						posTemp = new Position(r.getPosition().getX() + i, r
								.getPosition().getY());
					} else {
						posTemp = new Position(cible.getPosition().getX() + i,
								r.getPosition().getY());
					}
					strTemp = p.posToString(posTemp);
					posTemp = p.getCarte().get(strTemp);
					if (posTemp.estObstacle() || posTemp.estRobot()) {
						return false;
					}
				}
				return true;
			}

			else { // si ni les X, ni les Y sont egaux alors les robots ne sont
					// pas
					// sur la même ligne
				return false;
			}
		}

		/**
		 * Verifie si le robot peut se deplacer sur au moins une case
		 * 
		 * @return boolean true si il y a au moins un deplacement possible
		 */
		private static boolean deplacementPossible(Robot r) {
			Position tmp = r.getPosition();

			if (r instanceof Piegeur || r instanceof Tireur) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						tmp = p.getCarte()
								.get(p.posToString(new Position(r.getPosition()
										.getX() + i, r.getPosition().getY() + j)));
						if (tmp != null) {
							if ((tmp.estBase()
									&& tmp.getEquipe() == r.getEquipe() && checkTotalRobots(r))
									|| !(tmp.estObstacle() || tmp.estRobot() || tmp
											.estBase())) {
								return true;
							}
						}
					}
				}
			} else {
				tmp = p.getCarte().get(
						p.posToString(new Position(r.getPosition().getX() + 1,
								r.getPosition().getY())));
				if (tmp != null) {
					if (!(tmp.estObstacle() || tmp.estRobot())) {
						tmp = p.getCarte().get(
								p.posToString(new Position(r.getPosition()
										.getX() + 2, r.getPosition().getY())));
						if (tmp != null) {
							if (!(tmp.estObstacle() || tmp.estRobot())) {
								return true;
							}
						}
					}
				}
				tmp = p.getCarte().get(
						p.posToString(new Position(r.getPosition().getX() - 1,
								r.getPosition().getY())));
				if (tmp != null) {
					if (!(tmp.estObstacle() || tmp.estRobot())) {
						tmp = p.getCarte().get(
								p.posToString(new Position(r.getPosition()
										.getX() - 2, r.getPosition().getY())));
						if (tmp != null) {
							if (!(tmp.estObstacle() || tmp.estRobot())) {
								return true;
							}
						}
					}
				}
				tmp = p.getCarte().get(
						p.posToString(new Position(r.getPosition().getX(), r
								.getPosition().getY() + 1)));
				if (tmp != null) {
					if (!(tmp.estObstacle() || tmp.estRobot())) {
						tmp = p.getCarte().get(
								p.posToString(new Position(r.getPosition()
										.getX(), r.getPosition().getY() + 2)));
						if (tmp != null) {
							if (!(tmp.estObstacle() || tmp.estRobot())) {
								return true;
							}
						}
					}
				}
				tmp = p.getCarte().get(
						p.posToString(new Position(r.getPosition().getX(), r
								.getPosition().getY() - 1)));
				if (tmp != null) {
					if (!(tmp.estObstacle() || tmp.estRobot())) {
						tmp = p.getCarte().get(
								p.posToString(new Position(r.getPosition()
										.getX(), r.getPosition().getY() - 2)));
						if (tmp != null) {
							if (!(tmp.estObstacle() || tmp.estRobot())) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}

		/**
		 * Verifie le nombre total de robots en dehors de la base pour l'equipe
		 * du robot passe en parametre.
		 * 
		 * @return boolean true si il y a au moins 2 robots hors de la base
		 */
		private static boolean checkTotalRobots(Robot rob) {
			int equipe = rob.getEquipe();
			int cpt = 0;

			for (Robot r : Position.getPlateau().getListeRobot()) {
				if (r.getEquipe() == equipe && !r.getPosition().estBase()) {
					cpt++;
				}
			}
			return cpt > 1;
		}

		/**
		 * Sauvegarde la partie dans le fichier save/sauvegarte.txt
		 */
		public static void sauvegarde() {
			try {
				File save = new File("save/sauvegarde.txt");
				save.createNewFile();
				FileWriter s = new FileWriter(save);
				s.write(p.getLargeur() + " " + p.getHauteur() + " "
						+ p.getPercentObstacle() + " ");

				int cptJ1 = 0;
				int cptJ2 = 0;
				for (Robot r : robotsInit) {
					if (r.getEquipe() == 0
							&& (nbrIA.equals("0") || nbrIA.equals("1"))) {
						cptJ1++;
					} else if (r.getEquipe() == 1 && nbrIA.equals("0")) {
						cptJ2++;
					}
				}

				s.write(cptJ1 + " " + cptJ2 + " ");
				s.write(nbrIA + " ");

				for (Robot r : robotsInit) {
					if (r.getEquipe() == 0
							&& (nbrIA.equals("0") || nbrIA.equals("1"))
							|| (r.getEquipe() == 1 && nbrIA.equals("0"))) {
						s.write(r + " ");
					}
				}

				if (nbrIA.equals("1") || nbrIA.equals("2")) {
					s.write(IA1.getFormation() + " ");
				}

				if (nbrIA.equals("2")) {
					s.write(IA2.getFormation() + " ");
				}

				for (int i = 1; i <= p.getLargeur(); i++) {
					for (int k = 1; k <= p.getHauteur(); k++) {
						if (p.getCarte().get(p.posToString(new Position(i, k)))
								.estObstacle()) {
							s.write(p.posToString(new Position(i, k)));
							s.write(" ");
						}
					}
				}

				for (Action a : actions) {
					s.write(a + " ");
				}

				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Charge la partie a partir du fichier save/sauvegarde.txt
		 */
		public static void chargement() {
			try {

				File save = new File("save/sauvegarde.txt");
				save.createNewFile();
				Scanner s = new Scanner(save);
				int largeur = s.nextInt();
				int hauteur = s.nextInt();
				int obstacles = s.nextInt();
				int nbRobotsJ1 = s.nextInt();
				int nbRobotsJ2 = s.nextInt();

				p = new Plateau(largeur, hauteur, obstacles);
				nbrIA = s.next();

				if (nbrIA.equals("0") || nbrIA.equals("1")) {
					for (int i = 0; i < nbRobotsJ1; i++) {
						char tmp = s.next().charAt(0);
						if (tmp == 'C') {
							p.ajouterListeRobot(new Char(0));
						} else if (tmp == 'T') {
							p.ajouterListeRobot(new Tireur(0));

						} else {
							p.ajouterListeRobot(new Piegeur(0));
						}
					}
				}

				if (nbrIA.equals("0")) {
					for (int i = 0; i < nbRobotsJ2; i++) {
						char tmp = s.next().charAt(0);
						if (tmp == 'C') {
							p.ajouterListeRobot(new Char(1));
						} else if (tmp == 'T') {
							p.ajouterListeRobot(new Tireur(1));

						} else {
							p.ajouterListeRobot(new Piegeur(1));
						}
					}
				}

				if (nbrIA.equals("1")) {
					String form1 = s.next();
					initIA(nbrIA, form1, null);
				}
				if (nbrIA.equals("2")) {
					String form1 = s.next();
					String form2 = s.next();
					initIA(nbrIA, form1, form2);
				}

				String tmp = "";
				while (true) {
					try {
						tmp = s.next();
						p.getCarte().get(tmp).flipObstacle();
					} catch (Exception e) {
						break;
					}
				}

				int equipe = Integer.parseInt(tmp);
				int robot = s.nextInt();
				int action = s.nextInt();
				String cible = s.next();

				if (action == 0) {
					actions.add(new Deplacement(p.getRobot(robot), p
							.stringToPos(cible)));

				} else {
					actions.add(new Attaque(p.getRobot(robot), p
							.stringToPos(cible)));
				}

				if (!actions.isEmpty()) {

					if (actions.get(actions.size() - 1) instanceof Deplacement) {
						historique.setText(historique.getText()
								+ "\nLe robot "
								+ actions.get(actions.size() - 1).getRobot()
										.getId()
								+ " s'est deplace en "
								+ p.posToString(actions.get(actions.size() - 1)
										.getCible()) + "\n");
					} else {

						if (actions.get(actions.size() - 1).getRobot() instanceof Piegeur) {
							historique.setText(historique.getText()
									+ "\nLe robot "
									+ actions.get(actions.size() - 1)
											.getRobot().getId()

									+ " a pose une mine\n");
						} else {
							if (actions.get(actions.size() - 1).getCible()
									.getRobot() != null) {
								historique.setText(historique.getText()
										+ "\nLe robot "
										+ actions.get(actions.size() - 1)
												.getRobot().getId()

										+ " a attaque le robot "
										+ actions.get(actions.size() - 1)
												.getCible().getRobot().getId()
										+ "\n");
							}
						}
					}
				}

				while (s.hasNext()) {
					equipe = s.nextInt();
					robot = s.nextInt();
					action = s.nextInt();
					cible = s.next();

					if (action == 0) {
						actions.add(new Deplacement(p.getRobot(robot), p
								.stringToPos(cible)));

					} else {
						actions.add(new Attaque(p.getRobot(robot), p
								.stringToPos(cible)));
					}
					if (!actions.isEmpty()) {

						if (actions.get(actions.size() - 1) instanceof Deplacement) {
							historique.setText(historique.getText()
									+ "\nLe robot "
									+ actions.get(actions.size() - 1)
											.getRobot().getId()
									+ " s'est deplace en "
									+ p.posToString(actions.get(
											actions.size() - 1).getCible())
									+ "\n");
						} else {

							if (actions.get(actions.size() - 1).getRobot() instanceof Piegeur) {
								historique.setText(historique.getText()
										+ "\nLe robot "
										+ actions.get(actions.size() - 1)
												.getRobot().getId()

										+ " a pose une mine\n");
							} else {
								if (actions.get(actions.size() - 1).getCible()
										.getRobot() != null) {
									historique.setText(historique.getText()
											+ "\nLe robot "
											+ actions.get(actions.size() - 1)
													.getRobot().getId()

											+ " a attaque le robot "
											+ actions.get(actions.size() - 1)
													.getCible().getRobot()
													.getId() + "\n");
								}
							}
						}
					}
					p.recharges();
				}
				joueur = actions.get(actions.size() - 1).getRobot().getEquipe() == 0;

			} catch (Exception e) {
				e.printStackTrace();
				System.out
						.println("Sauvegarde corrompue, veuillez redemarrer le jeu");
				System.exit(0);
			}
		}

		/**
		 * Charge une ou deux composition(s) pour l'intelligence artificielle
		 */
		public static void initIA(String nbrIA, String form1, String form2) {
			if (nbrIA.equals("1")) {
				IA1 = new IntelligenceArtificielle(p, 1, form1);
				IA2 = null;

				for (Robot rob : IA1.getRobots()) {
					p.ajouterListeRobot(rob);
				}

			}

			else if (nbrIA.equals("2")) {
				IA1 = new IntelligenceArtificielle(p, 0, form1);
				IA2 = new IntelligenceArtificielle(p, 1, form2);

				for (Robot rob : IA1.getRobots()) {
					p.ajouterListeRobot(rob);
				}

				for (Robot rob : IA2.getRobots()) {
					p.ajouterListeRobot(rob);
				}
			}

			else {
				IA1 = null;
				IA2 = null;
			}
		}

		/**
		 * Cree une ou plusieurs composition(s) pour l'intelligence artificielle
		 */
		public static void initIA(String nbrIA) {
			if (nbrIA.equals("1")) {
				IA1 = new IntelligenceArtificielle(p, 1);
				IA2 = null;

				for (Robot rob : IA1.getRobots()) {
					p.ajouterListeRobot(rob);
				}

			}

			else if (nbrIA.equals("2")) {
				IA1 = new IntelligenceArtificielle(p, 0);
				IA2 = new IntelligenceArtificielle(p, 1);

				for (Robot rob : IA1.getRobots()) {
					p.ajouterListeRobot(rob);
				}

				for (Robot rob : IA2.getRobots()) {
					p.ajouterListeRobot(rob);
				}
			}

			else {
				IA1 = null;
				IA2 = null;
			}
		}

		/**
		 * Verifie que le joueur passe en parametre est humain
		 * 
		 * @return boolean true si le joueur est humain
		 */
		public static boolean isJoueur(String joueur, String nbrIA) {
			if (joueur == "J1" && (nbrIA.equals("1") || nbrIA.equals("0"))) {
				return true;
			}

			else if (joueur == "J2" && nbrIA.equals("0")) {
				return true;
			}

			else {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new IHM();
				if (joueur) {
					affJoueurCourant.setText("Joueur courant : J1");
				} else {
					affJoueurCourant.setText("Joueur courant : J2");
				}
			};
		});
	}
}

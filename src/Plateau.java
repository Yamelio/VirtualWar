import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Plateau {
	private Map<String, Position> carte;
	private List<Robot> robots;
	private int hauteur;
	private int largeur;
	private int percentObstacle;

	/**
	 * Constructeur de la classe Plateau
	 * 
	 * @param largeur
	 *            Initialise la largeur du plateau
	 * @param hauteur
	 *            Initialise la hauteur du plateau
	 */
	public Plateau(int largeur, int hauteur, int obstacles) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.percentObstacle = obstacles;
		initCarte();
		initObstacles();
		robots = new ArrayList<Robot>(10);
	}

	/**
	 * Fonction d'initialisation de la carte
	 */
	public void initCarte() {
		carte = new HashMap<String, Position>();
		for (int i = 1; i <= largeur; i++) {
			for (int j = 1; j <= hauteur; j++) {
				if (i == 1 & j == 1) {
					carte.put(posToString(new Position(i, j)),
							new Base(i, j, 0));
				} else if (i == largeur && j == hauteur) {
					carte.put(posToString(new Position(i, j)),
							new Base(i, j, 1));
				}

				else {
					carte.put(posToString(new Position(i, j)), new Position(i,
							j));
				}
			}
		}
		/*
		 * carte.remove("A1"); carte.put("A1", new Base(1, 1, 0));
		 * carte.remove(max); carte.put(max, new Base(largeur, hauteur, 1));
		 */
		Position.setPlateau(this);

	}

	/**
	 * Fonction pour convertir la String en position
	 * 
	 * @param Position
	 *            La position à convertir
	 * @return La String sous la forme "B11"
	 */
	public String posToString(Position p) {
		String s = "";
		s += (char) ((char) p.getX() + 64); // +64 car les lettres se trouvent
											// � partir de 65
											// dans la table ASCII
		s += p.getY(); // Pas besoin de convertion, les ordonnées sont déjà
						// en
						// int
		return s;
	}

	/**
	 * Fonction pour convertir la Position en String
	 * 
	 * @param String
	 *            La String à convertir, sous la forme "C4"
	 * @return La Position voulu
	 */
	public Position stringToPos(String s) {
		int x = s.charAt(0) - 64; // On converti
									// l'abscisse en int
		int y = Integer.parseInt(s.substring(1)); // On converti l'ordonné en
													// int
		return new Position(x, y);
	}

	/**
	 * Crée un chemin libre pour pouvoir poser des obstacles autour
	 * 
	 */
	private void initObstacles() {
		Random r = new Random();
		List<Position> liste = new ArrayList<Position>(); // Liste des position
															// à laisser libre
		Position p = new Position(1, 1); // On commence en haut à gauche
		liste.add(p);// La position en haut à gauche est laissé libre
		liste.add(new Position(largeur, hauteur)); // La position en bas à
													// droite est laissé libre

		while (p.getX() != largeur && p.getY() != hauteur) { // Temps qu'on est
																// pas arriver
																// dans le coin
																// en bas à
																// gauche
			if (r.nextBoolean()) { // une chance sur deux qu'il se déplace
									// verticalement
				if (p.getX() + 1 <= largeur) {
					p = new Position(p.getX() + 1, p.getY());
				}
			} else {
				if (p.getY() + 1 <= hauteur) {
					p = new Position(p.getX(), p.getY() + 1);
				} else {
					if (p.getX() + 1 <= largeur) {
						p = new Position(p.getX() + 1, p.getY());
					}
				}
			}
			liste.add(p); // On ajoute a la liste

		}
		int nbObstacle = (int) (getSurface() * (percentObstacle / 100.0));

		int randX;
		int randY;

		while (nbObstacle > 0) { // tant qu'il reste des obsacles à ajouter

			while (liste.contains(p)) {
				randX = r.nextInt(largeur) + 1;
				randY = r.nextInt(hauteur) + 1;
				p = new Position(randX, randY);
			}

			--nbObstacle; // On décremente le nombre d'obsacle à
							// ajouter
			liste.add(p);
			p.flipObstacle();
			carte.remove(posToString(p));
			carte.put(posToString(p), p);
		}
		Position.setPlateau(this);
	}

	/**
	 * Procedure qui affiche le plateau
	 * 
	 * @return String Le plateau de jeu en ascii art
	 */
	public String toString() {
		String s = "\t";
		for (int k = 0; k < largeur; k++) {
			s += " " + (char) ((int) 'A' + k) + " "; // Pour mettre les
														// coordonne en
														// abscisses
		}
		s += "\n";

		for (int i = 1; i <= hauteur; i++) {
			s += "\t";
			for (int j = 1; j <= largeur; j++) {
				s += "+--";
			}
			s += "+" + "\n" + i + "\t";
			for (int k = 1; k <= largeur; k++) {
				s += "|"
						+ carte.get(posToString((Position) new Position(k, i)))
								.getContenu();
			}
			s += "|\n";
		}
		s += "\t";
		for (int j = 0; j < largeur; j++) {
			s += "+--";
		}
		s += "+";

		return s;
	}

	public int getSurface() {
		return largeur * hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getPercentObstacle() {
		return percentObstacle;
	}

	public Map<String, Position> getCarte() {
		return carte;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setPercentObstacle(int percentObstacle) {
		this.percentObstacle = percentObstacle;
	}

	public void ajouterListeRobot(Robot r) {
		this.robots.add(r);
		if (r.getEquipe() == 0) {
			((Base) carte.get("A1")).deplaceSur(r);
		} else {
			((Base) carte.get(posToString(new Position(largeur, hauteur))))
					.deplaceSur(r);
		}
	}

	public void retirerListeRobot(Robot r) {
		this.robots.remove(r);
	}

	public List<Robot> getListeRobot() {
		return this.robots;
	}

	public void afficherRobotsJ1() {
		String res = "Equipe 1 :\t";
		Robot r;
		for (int i = 0; i < robots.size(); i++) {
			r = robots.get(i);
			if (r.getEquipe() == 0) {
				res += r.toString();
				if (((Base) carte.get("A1")).getRobotsInBase().contains(
						robots.get(i))) {
					res += "(b)";
				}

				res += "\t[";
				int energiemax;
				if (r instanceof Char) {
					energiemax = Constantes.getEnergieInitialeChar();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else if (r instanceof Piegeur) {
					energiemax = Constantes.getEnergieInitialePiegeur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else {
					energiemax = Constantes.getEnergieInitialeTireur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				}

				res += "] " + r.getEnergie() + "/" + energiemax + "\n\t\t";
			}
		}
		System.out.println(res);
	}

	public void afficherRobotsJ2() {
		String res = "Equipe 2 :\t";
		Robot r;
		for (int i = 0; i < robots.size(); i++) {
			r = robots.get(i);
			if (r.getEquipe() == 1) {
				res += r.toString();
				if (((Base) carte
						.get(posToString(new Position(largeur, hauteur))))
						.getRobotsInBase().contains(robots.get(i))) {
					res += "(b)";
				}
				res += "\t[";
				int energiemax;
				if (r instanceof Char) {
					energiemax = Constantes.getEnergieInitialeChar();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else if (r instanceof Piegeur) {
					energiemax = Constantes.getEnergieInitialePiegeur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else {
					energiemax = Constantes.getEnergieInitialeTireur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() > k - energiemax / 2) {
							res += "|";
						} else {
							res += " ";
						}
					}

				}

				res += "] " + r.getEnergie() + "/" + energiemax + "\n\t\t";
			}
		}
		System.out.println(res);
	}
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * La classe Plateau dÃ©finit le plateau de jeu ou se dÃ©roulera la partie
 */
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

		Position.setPlateau(this);

	}

	/**
	 * Fonction pour convertir la String en position
	 * 
	 * @param Position
	 *            La position ÃƒÂ  convertir
	 * @return La String sous la forme "B11"
	 */
	public String posToString(Position p) {
		String s = "";
		s += (char) ((char) p.getX() + 64); // +64 car les lettres se trouvent
											// Ã  partir de 65
											// dans la table ASCII
		s += p.getY(); // Pas besoin de convertion, les ordonnÃƒÂ©es sont
						// dÃƒÂ©jÃƒÂ 
						// en
						// int
		return s;
	}

	/**
	 * Fonction pour convertir la Position en String
	 * 
	 * @param String
	 *            La String ÃƒÂ  convertir, sous la forme "C4"
	 * @return La Position voulu
	 */
	public Position stringToPos(String s) {
		int x = s.charAt(0) - 64; // On converti
									// l'abscisse en int
		int y = Integer.parseInt(s.substring(1)); // On converti l'ordonnÃƒÂ© en
													// int
		return new Position(x, y);
	}

	/**
	 * CrÃƒÂ©e un chemin libre pour pouvoir poser des obstacles autour
	 * 
	 */
	public void initObstacles() {
		Random r = new Random();
		List<Position> chemin = new ArrayList<Position>();
		List<Position> obstacles = new ArrayList<Position>();

		Position p = new Position(2, 2);
		chemin.add(new Position(1, 1));
		chemin.add(new Position(1, 2));
		chemin.add(new Position(2, 1));
		chemin.add(p);

		chemin.add(new Position(largeur, hauteur));
		chemin.add(new Position(largeur - 1, hauteur));
		chemin.add(new Position(largeur, hauteur - 1));

		while (p.getX() != largeur - 1 || p.getY() != hauteur - 1) {
			Position init = p;
			if (r.nextBoolean()) {
				if (p.getX() + 1 <= largeur - 1) {
					p = new Position(p.getX() + 1, p.getY());
				} else {
					if (p.getY() + 1 <= hauteur - 1) {
						p = new Position(p.getX(), p.getY() + 1);
					}
				}
			} else {
				if (p.getY() + 1 <= hauteur - 1) {
					p = new Position(p.getX(), p.getY() + 1);
				} else {
					if (p.getX() + 1 <= largeur - 1) {
						p = new Position(p.getX() + 1, p.getY());
					}
				}
			}
			if (!contient(chemin, p)) {
				chemin.add(new Position(p.getX(), p.getY()));
			} else {
				p = init;
			}
		}
		int nbObstacle = (int) (getSurface() * (percentObstacle / 100.0));

		while (nbObstacle > 0) { // tant qu'il reste des obsacles ÃƒÂ  ajouter
			do {
				p = new Position(r.nextInt(largeur) + 1, r.nextInt(hauteur) + 1);
			} while (contient(obstacles, p) || contient(chemin, (p)));

			--nbObstacle;
			obstacles.add(p);

			carte.get(posToString(p)).flipObstacle();
		}
		Position.setPlateau(this);
	}

	private boolean contient(List<Position> liste, Position p) {
		for (Position pos : liste) {
			if (pos.getX() == p.getX() && pos.getY() == p.getY()) {
				return true;
			}
		}
		return false;
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

	/**
	 * Procedure qui ajoute le robot entré en paramètre à la liste des robots
	 * présent sur le plateau
	 * 
	 * @param Robot
	 */
	public void ajouterListeRobot(Robot r) {
		this.robots.add(r);
		if (r.getEquipe() == 0) {
			((Base) carte.get("A1")).deplaceSur(r);
		} else {
			((Base) carte.get(posToString(new Position(largeur, hauteur))))
					.deplaceSur(r);
		}
	}

	/**
	 * Procedure qui retire le robot entrée en paramètre de la liste des robots
	 * présent sur le plateau
	 * 
	 * @param Robot
	 */
	public void retirerListeRobot(Robot r) {
		this.robots.remove(r);
	}

	public List<Robot> getListeRobot() {
		return this.robots;
	}

	/**
	 * Procedure qui affiche les robots de l'équipe 1
	 */
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
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else if (r instanceof Piegeur) {
					energiemax = Constantes.getEnergieInitialePiegeur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else {
					energiemax = Constantes.getEnergieInitialeTireur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				}

				res += "] " + r.getEnergie() + "/" + energiemax;

				if (r instanceof Piegeur) {
					res += "  // Mines : " + ((Piegeur) r).getNbMines() + "/"
							+ Constantes.getMinesInit();
				}

				res += "\n\t\t";
			}
		}
		System.out.println(res);
	}

	/**
	 * Procedure qui affiche les robots de l'équipe 2
	 */
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
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else if (r instanceof Piegeur) {
					energiemax = Constantes.getEnergieInitialePiegeur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				} else {
					energiemax = Constantes.getEnergieInitialeTireur();
					for (int k = 0; k < energiemax / 2; k++) {
						if (r.getEnergie() / 2 > k) {
							res += "|";
						} else {
							res += " ";
						}
					}

				}

				res += "] " + r.getEnergie() + "/" + energiemax;

				if (r instanceof Piegeur) {
					res += "  // Mines : " + ((Piegeur) r).getNbMines() + "/"
							+ Constantes.getMinesInit();
				}

				res += "\n\t\t";
			}
		}
		System.out.println(res);
	}

	/**
	 * Procedure qui recharges les robots présent sur les bases
	 */
	public void recharges() {
		((Base) carte.get("A1")).recharge();
		((Base) carte.get(posToString(new Position(largeur, hauteur))))
				.recharge();
	}

	public Robot getRobot(int i) {
		for (Robot r : robots) {
			if (r.getId() == i) {
				return r;
			}
		}
		return null;
	}
}

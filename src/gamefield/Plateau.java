package virtualwars.gamefield;

import java.util.ArrayList;
import java.util.Random;

import virtualwars.Constante;
import virtualwars.entity.Char;
import virtualwars.entity.Piegeur;
import virtualwars.entity.Robot;
import virtualwars.entity.Tireur;
import virtualwars.entity.actions.Attaque;
import virtualwars.entity.actions.Deplacement;

public class Plateau {

	private Cellule[][] cellules;
	private GraphePlateau graphe;
	private ArrayList<Robot> robots;
	private int hauteur;
	private int largeur;

	/**
	 * Constructeur de la classe Plateau
	 * 
	 * @param largeur
	 *            Initialise la largeur du plateau
	 * @param hauteur
	 *            Initialise la hauteur du plateau
	 * @param percentObstacle
	 *            Initialise le pourcentage d'obstacle present sur le plateau
	 */
	public Plateau(int largeur, int hauteur, int percentObstacle) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		robots = new ArrayList<Robot>();
		int checksum = 0;
		int countGen = 0;
		while (!plateauValide(checksum)) {
			++countGen;
			cellules = new Cellule[largeur][hauteur];
			SommetPlateau[][] sommets = new SommetPlateau[largeur][hauteur];
			int equipe = 1;
			for (int i = 0; i < largeur; ++i) {
				for (int j = 0; j < hauteur; ++j) {
					if (i == 0 || i + 1 == largeur || j == 0
							|| j + 1 == hauteur) {
						cellules[i][j] = new Mur(i, j);
					} else {
						if ((i == 1 && j == 1)
								|| (i == largeur - 2 && j == hauteur - 2)) {
							cellules[i][j] = new Base(i, j, equipe);
							++equipe;
						} else {
							cellules[i][j] = new Case(i, j);
						}
						sommets[i][j] = new SommetPlateau(cellules[i][j]);
					}
				}
			}
			initObstacles(percentObstacle, sommets);
			graphe = new GraphePlateau(sommets);
			checksum = graphe.checksumBases(sommets[1][1]);
		}
		System.out.println(countGen
				+ " tentative(s) de génération de plateau");
	}

	/**
	 * A completer
	 * 
	 * @param checksum
	 * @return
	 */
	public boolean plateauValide(int checksum) {
		return checksum == Constante.EQUIPE_ALLIE + Constante.EQUIPE_ENNEMIE;
	}

	/**
	 * 
	 * @return Retourne la taille du plateau
	 */
	public int getSize() {
		return largeur * hauteur;
	}

	/**
	 * Pose une mine de l'equipe "equipe" sur les Coordonnees "coord"
	 * 
	 * @param coord
	 *            Coordonnees ou placer la mine
	 * @param equipe
	 *            Equipe qui pose la mine
	 */
	public void poserMine(Coordonnees coord, int equipe) {
		cellules[coord.getLargeur()][coord.getHauteur()].ajouteMine(equipe);
	}

	/**
	 * A completer
	 * 
	 * @param percent
	 * @param sommets
	 */
	private void initObstacles(int percent, SommetPlateau[][] sommets) {
		Random r = new Random();
		int nb = ((this.getSize() - (hauteur * 2 + largeur * 2 - 4)) * percent) / 100;
		while (nb > 0) {
			int i = (r.nextInt(hauteur - 1)) + 1;
			int j = (r.nextInt(largeur - 1)) + 1;
			if (!cellules[j][i].estMur() && cellules[j][i].estBase() == 0) {
				nb--;
				cellules[j][i] = new Mur(j, i);
				sommets[j][i] = null;
			}
		}
	}

	/**
	 * Verifie si la coordonnees pass� en parametre est un mur
	 * 
	 * @param coord
	 *            La coordonnee a verifier
	 * @return true si c'est un mur
	 */
	public boolean estMur(Coordonnees coord) {
		return cellules[coord.getLargeur()][coord.getHauteur()].estMur();
	}

	/**
	 * Verifie si la coordonnee passe en parametre est une base et a qui elle
	 * appartien
	 * 
	 * @param coord
	 *            La coordonnee a verifier
	 * @return le numero de l'equipe a qui elle appartient si c'est une base
	 */
	public int estBase(Coordonnees coord) {
		return cellules[coord.getLargeur()][coord.getHauteur()].estBase();
	}

	/**
	 * Verifie si la coordonnee passe en parametre est une mine et a qui elle
	 * appartient
	 * 
	 * @param coord
	 *            La coordonnee a verifier
	 * @return Le numero de l'equipe qui l'a pos� si c'est une mine
	 */
	public int contientMine(Coordonnees coord) {
		return cellules[coord.getLargeur()][coord.getHauteur()].contientMine();
	}
	
	public ArrayList<Robot> getRobotsCellule(Coordonnees coor) {
		if(estBase(coor) != 0) {
			return ((Base)cellules[coor.getLargeur()][coor.getHauteur()]).getRobotsInBase();
		} else {
			ArrayList<Robot> robots = new ArrayList<Robot>();
			if(contenu(coor) != null) robots.add(contenu(coor));
			return robots;
		}
	}

	/**
	 * Trouve le robot contenu dans la cellule donnee.
	 * 
	 * @param coord
	 *            la coordonnee a verifier
	 * @return le robot qui est contenu dans cette cellule
	 */
	public Robot contenu(Coordonnees coord) {
		return cellules[coord.getLargeur()][coord.getHauteur()].getContenu();
	}

	/**
	 * deplace le robot selectionne a la coordonnee donnee.
	 * 
	 * @param coor
	 *            coordonnee ou deplacer le robot
	 * @param robot
	 *            robot a deplacer
	 */
	public void deplaceRobot(Coordonnees coor, Robot robot) {
		cellules[coor.getLargeur()][coor.getHauteur()].deplaceSur(robot);
		robot.setCoordonnees(coor);
	}

	/**
	 * Execute les mises à jour des robots lors d'un tour (régénération base)
	 */
	public void tour() {
		for (Robot r : robots) {
			Coordonnees coor = r.getCoordonnees();
			if (cellules[coor.getLargeur()][coor.getHauteur()].estBase() != 0) {
				r.regenEnergie();
				if (r instanceof Piegeur)
					((Piegeur) r).regenMine();
			}
		}
	}

	/**
	 * Retourne l'équipe vainqueur
	 * 
	 * @return Retourne l'équipe vainqueur si il y en a une, 0 sinon
	 */
	public int getVainqueur() {
		int vainqueur = 0;
		for (Robot r : robots) {
			if (r.getEnergie() > 0 && vainqueur == 0)
				vainqueur = r.getEquipe();
			else if (r.getEnergie() > 0 && vainqueur != r.getEquipe())
				return 0;
		}
		return vainqueur;
	}

	/**
	 * @return Retourne le plateau et la description des robots
	 */
	public String toString() {
		String display = "";
		for (int j = 0; j < hauteur; ++j) {
			for (int i = 0; i < largeur; ++i) {
				display += "+---";
			}
			display += "+\n";
			for (int i = 0; i < largeur; ++i) {
				Coordonnees coord = new Coordonnees(i, j);
				if (estMur(coord))
					display += "||||";
				else {
					if (estBase(coord) != 0) {
						if (estBase(coord) == Constante.EQUIPE_ALLIE)
							display += "| B ";
						else
							display += "| b ";
					} else if (contientMine(coord) != 0) {
						if (contientMine(coord) == Constante.EQUIPE_ALLIE)
							display += "| X ";
					} else {
						if (contenu(coord) != null)
							display += "| " + contenu(coord).getType() + " ";
						else
							display += "|   ";
					}
				}
			}
			display += "|\n";
		}

		for (int i = 0; i < largeur; ++i) {
			display += "+---";
		}
		display += "+\n";
		// display += cellules[1][1].toString();
		display += "\nEquipe ALLIE ("+Constante.EQUIPE_ALLIE+")\n==========================================\n\n"
				+ getDisplayFor(Constante.EQUIPE_ALLIE)
				+ "==========================================\n";
		display += "\nEquipe ENNEMIE ("+Constante.EQUIPE_ENNEMIE+")\n==========================================\n\n"
				+ getDisplayFor(Constante.EQUIPE_ENNEMIE)
				
				+ "==========================================\n";
		return display;
	}

	/**
	 * Retourne la chaine de caractères de description d'une équipe
	 * 
	 * @param equipe
	 *            Equipe à décrire
	 * @return Description de l'équipe
	 */
	public String getDisplayFor(int equipe) {
		String s = "";
		for (int i = 0; i < largeur; ++i) {
			for (int j = 0; j < hauteur; ++j) {
				if (cellules[i][j].estBase() == equipe
						|| (cellules[i][j].getContenu() != null && cellules[i][j]
								.getContenu().getEquipe() == equipe)) {
					s += cellules[i][j].toString() + "\n";
				}
			}
		}
		s += "\n";
		return s;
	}

	/**
	 * Vide la case situe a la coordonnee donee
	 * 
	 * @param coor
	 *            coordonnee a vider
	 */
	public void videCase(Coordonnees coor) {
		cellules[coor.getLargeur()][coor.getHauteur()].videCase();
	}

	/**
	 * verifie si la partie est finie.
	 * 
	 * @return return true si la partie est finie
	 */
	public boolean partieFini() {
		return getVainqueur() != 0;
	}

	/**
	 * Initialise les robots
	 * 
	 * @param robots
	 *            ArrayList des robots selectionne.
	 */
	public void initialiserRobots(ArrayList<Robot> robots) {
		for (int i = 0; i < largeur; ++i) {
			for (int j = 0; j < hauteur; ++j) {
				if (cellules[i][j] instanceof Base) {
					for (Robot r : robots) {
						if (r.getEquipe() == cellules[i][j].estBase()) {
							deplaceRobot(new Coordonnees(i, j), r);
							this.robots.add(r);
						}
					}
				}
			}
		}
	}
/**
 * 
 * main de la classe plateau
 */
	public static void main(String[] args) {
		ArrayList<Robot> robots = new ArrayList<Robot>();
		Plateau p = new Plateau(10, 10, 10);
		Object l = p;
		Vue v = new Vue((Plateau) l, Constante.EQUIPE_ALLIE);
		Vue v2 = new Vue((Plateau) l, Constante.EQUIPE_ENNEMIE);
		Robot r = new Char(v, 1, 1, Constante.EQUIPE_ALLIE);
		Robot r2 = new Tireur(v, 1, 1, Constante.EQUIPE_ALLIE);
		Robot r3 = new Piegeur(v, 1, 1, Constante.EQUIPE_ALLIE);
		Robot r4 = new Tireur(v, 1, 1, Constante.EQUIPE_ALLIE);
		Robot r5 = new Tireur(v2, 1, 1, Constante.EQUIPE_ENNEMIE);
		robots.add(r);
		robots.add(r2);
		robots.add(r3);
		robots.add(r4);
		robots.add(r5);
		p.initialiserRobots(robots);
		System.out.println(p.toString());

		// System.out.println(r.toString());
		Deplacement d = new Deplacement(r3, Constante.DIRECTION_SUD);
		d.agit();
		p.tour();
		Attaque a = new Attaque(r3, Constante.DIRECTION_SUD);
		a.agit();
		d.agit();
		/*
		 * d = new Deplacement(r, Constante.DIRECTION_NORD); d.agit(); d = new
		 * Deplacement(r, Constante.DIRECTION_NORD); d.agit();
		 */
		System.out.println(p.toString());

	}
}

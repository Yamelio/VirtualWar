import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Les Quatre Cavaliers de l'Apocalypse
 */

public class Main {

	private static List<Action> actions;
	private static Plateau p;
	private static List<Robot> robotsInit;

	/**
	 * C'est le main
	 */
	public static void main(String[] args) {
		Random r = new Random();
		actions = new ArrayList<Action>();
		Scanner s = new Scanner(System.in);
		boolean joueur = r.nextBoolean(); // J1=true, J2=false
		Robot robotChoisi = null;
		Position choixCible = null;

		System.out.println("Voulez vous charger une partie ? (o/n)");
		String rep;
		do {
			rep = s.next();
		} while (!(rep.equals("o") || rep.equals("n")));

		if (rep.equals("o")) {
			chargement();

			robotChoisi = actions.get(actions.size() - 1).getRobot();
			choixCible = actions.get(actions.size() - 1).getCible();
			if (actions.get(actions.size() - 1).getRobot().getEquipe() == 0) {
				joueur = false;
			} else {
				joueur = true;
			}
		} else {

			int nbTireurJ1 = 10;
			int nbPiegeurJ1 = 10;
			int nbCharJ1 = 10;
			do {
				try {
					System.out
							.println("Joueur 1, choisissez vos robots(Maximum 5) :\n\tTireur : ");
					nbTireurJ1 = s.nextInt();
					System.out.println("\n\tChar : ");
					nbCharJ1 = s.nextInt();
					System.out.println("\n\tPiegeur : ");
					nbPiegeurJ1 = s.nextInt();
				} catch (Exception e) {
					System.out.println("Erreur de saisie");
					s.next();
				}
			} while (nbTireurJ1 + nbCharJ1 + nbPiegeurJ1 > 5
					|| nbTireurJ1 + nbCharJ1 + nbPiegeurJ1 <= 0);
			int nbTireurJ2 = 10;
			int nbCharJ2 = 10;
			int nbPiegeurJ2 = 10;

			do {
				try {
					System.out
							.println("\n\nJoueur 2, choisissez vos robots(Maximum 5) :\n\tTireur : ");
					nbTireurJ2 = s.nextInt();
					System.out.println("\n\tChar : ");
					nbCharJ2 = s.nextInt();
					System.out.println("\n\tPiegeur : ");
					nbPiegeurJ2 = s.nextInt();
				} catch (Exception e) {
					System.out.println("Erreur de saisie");
					s.next();
				}
			} while (nbTireurJ2 + nbCharJ2 + nbPiegeurJ2 > 5
					|| nbTireurJ2 + nbCharJ2 + nbPiegeurJ2 <= 0);
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
			} while (largeur < 5 || largeur > 26 || hauteur < 5 || hauteur > 26);
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
		}
		robotsInit = new ArrayList<Robot>(p.getListeRobot());

		int fin = 2;
		String joueurCourant; // J1 ou J2

		int choixAction;

		Position.setPlateau(p);
		Vue vueJ1 = new Vue(p, 0);
		Vue vueJ2 = new Vue(p, 1);
		boolean saisieOk = false;

		while (fin >= 2) {

			for (int i = 0; i < 20; i++) {
				System.out.println("");
			}

			if (joueur) {
				joueurCourant = "J1";
			} else {
				joueurCourant = "J2";
			}
			if (joueur) {
				System.out.println(vueJ1);
			} else {
				System.out.println(vueJ2);
			}
			p.afficherRobotsJ1();
			p.afficherRobotsJ2();

			if (!actions.isEmpty()) {
				if (actions.get(actions.size() - 1) instanceof Deplacement) {
					System.out.println("Le robot " + robotChoisi.getId()
							+ " s'est deplace en " + p.posToString(choixCible));
				} else {
					if (robotChoisi instanceof Piegeur) {
						System.out.println("Le robot " + robotChoisi.getId()
								+ " a posé une mine");
					} else {
						System.out.println("Le robot " + robotChoisi.getId()
								+ " a attaque le robot "
								+ choixCible.getRobot().getId());
					}
				}
			}
			do {
				try {
					System.out

							.println("C'est a  "
									+ joueurCourant
									+ " de jouer !\nselectionnez le numero du robot de votre équipe que vous souhaitez utiliser, ainsi que son action");
					do {
						robotChoisi = p.getListeRobot().get(s.nextInt());
					} while ((robotChoisi.getEquipe() == 1 && joueur)
							|| (robotChoisi.getEquipe() == 0 && !joueur));
					if (robotChoisi instanceof Piegeur) {
						System.out
								.println("\t1- Poser une mine\n\t2- Se deplacer");
					} else {
						System.out.println("\t1- Attaquer\n\t2- Se deplacer");
					}
					choixAction = s.nextInt();
					System.out.println("Ou ?");
					s.nextLine();
					choixCible = p.stringToPos(s.nextLine());
					if (choixAction == 1) {
						try {
							actions.add(new Attaque(robotChoisi, choixCible));
							saisieOk = true;
						} catch (Erreur e) {
							System.out.println(e.getMessage());
							saisieOk = false;
						}
					} else {
						try {
							actions.add(new Deplacement(robotChoisi, choixCible));
							saisieOk = true;
						} catch (Erreur e) {
							System.out.println(e.getMessage());
							saisieOk = false;
						}
					}
				} catch (Exception e) {
					System.out.println("Erreur de saisie");
					s.next();
					saisieOk = false;
				}
			} while (!saisieOk);

			joueur = !joueur;
			p.recharges();
			sauvegarde();
			fin = checkFin();

		}

		for (int i = 0; i < 20; i++) {
			System.out.println("");
		}

		System.out.println(p);
		switch (fin) {

		case 1:
			p.afficherRobotsJ2();
			System.out.println("Joueur 2 a gagné !");
			break;

		case 0:
			p.afficherRobotsJ1();
			System.out.println("Joueur 1 a gagné !");
			break;

		case -1:
			System.out.println("Match nul !");
			break;
		}
	}

	/**
	 * Vérifie si la partie est finie
	 * 
	 * @return int Entier correspondant à la situation
	 */
	private static int checkFin() {
		int aliveJ1 = 0;
		int aliveJ2 = 0;
		List<Robot> toRemove = new ArrayList<Robot>();
		for (Robot r : Position.getPlateau().getListeRobot()) {
			if (r.getEnergie() > 0) {
				if (r.getEquipe() == 0) {
					aliveJ1++;
				} else {
					aliveJ2++;
				}
			} else {
				toRemove.add(Position.getPlateau().getListeRobot()
						.get(r.getId()));
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
				if (r.getEquipe() == 0) {
					cptJ1++;
				} else {
					cptJ2++;
				}
			}

			s.write(cptJ1 + " " + cptJ2 + " ");

			for (Robot r : robotsInit) {
				s.write(r + " ");
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

			// int nbObstacle = (int) ((largeur * hauteur) * (obstacles /
			// 100.0));
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
				actions.add(new Attaque(p.getRobot(robot), p.stringToPos(cible)));
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

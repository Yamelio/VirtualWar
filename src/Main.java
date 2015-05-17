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
	private static String nbrIA = "0";
	private static IntelligenceArtificielle IA1 = null;
	private static IntelligenceArtificielle IA2 = null;

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
		Robot robotCible = null;

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

			initIA(nbrIA);

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
			if (joueur && (nbrIA.equals("0") || nbrIA.equals("1"))) {
				System.out.println(vueJ1);
			} else if (!joueur && nbrIA.equals("0")) {
				System.out.println(vueJ2);
			} else if (nbrIA.equals("2")) {
				System.out.println(p);
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

						+ " a pose une mine");
					} else {
						if (robotCible != null) {
							System.out.println("Le robot "
									+ robotChoisi.getId()

									+ " a attaque le robot "
									+ robotCible.getId());
						}
					}
				}
			}

			if (isJoueur(joueurCourant, nbrIA)) {
				do {
					try {
						System.out

								.println("C'est a "
										+ joueurCourant
										+ " de jouer !\nselectionnez le numero du robot de votre equipe que vous souhaitez utiliser, ainsi que son action");
						do {
							String saisie = s.next();
							saisie = saisie.charAt(saisie.length() - 1) + "";
							int nbrSaisi = Integer.parseInt(saisie);
							robotChoisi = p.getListeRobot().get(nbrSaisi);

						} while ((robotChoisi.getEquipe() == 1 && joueur)
								|| (robotChoisi.getEquipe() == 0 && !joueur));
						if (robotChoisi instanceof Piegeur) {
							System.out
									.println("\t1- Poser une mine\n\t2- Se deplacer");
						} else {
							System.out
									.println("\t1- Attaquer\n\t2- Se deplacer");
						}
						choixAction = s.nextInt();
						System.out.println("Ou ?");
						s.nextLine();
						choixCible = p.stringToPos(s.nextLine());
						if (choixAction == 1) {
							try {
								actions.add(new Attaque(robotChoisi, choixCible));
								if (!(robotChoisi instanceof Piegeur)) {
									robotCible = robotsInit.get(choixCible
											.getRobot().getId());
								}
								saisieOk = true;
							} catch (Erreur e) {
								System.out.println(e.getMessage());
								saisieOk = false;
							}
						} else {
							try {
								actions.add(new Deplacement(robotChoisi,
										choixCible));
								saisieOk = true;
							} catch (Erreur e) {
								saisieOk = false;
							}
						}
					} catch (Exception e) {
						System.out.println("Erreur de saisie");
						s.next();
						saisieOk = false;
					}

				} while (!saisieOk);

			}

			else {
				if (nbrIA.equals("1")) {
					Action act = IA1.Jouer();
					actions.add(act);
					robotChoisi = act.getRobot();
					choixCible = act.getCible();
					if (act instanceof Attaque
							&& !(robotChoisi instanceof Piegeur)) {
						robotCible = robotsInit.get(choixCible.getRobot()
								.getId());
					}
				}

				if (nbrIA.equals("2") && joueurCourant == "J1") {
					Action act = IA1.Jouer();
					actions.add(act);
					robotChoisi = act.getRobot();
					choixCible = act.getCible();
					if (act instanceof Attaque
							&& !(robotChoisi instanceof Piegeur)) {
						robotCible = robotsInit.get(choixCible.getRobot()
								.getId());
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}

				if (nbrIA.equals("2") && joueurCourant == "J2") {
					Action act = IA2.Jouer();
					actions.add(act);
					robotChoisi = act.getRobot();
					choixCible = act.getCible();
					if (act instanceof Attaque
							&& !(robotChoisi instanceof Piegeur)) {
						robotCible = robotsInit.get(choixCible.getRobot()
								.getId());
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}

			}

			joueur = !joueur;
			p.recharges();
			sauvegarde();
			fin = checkFin();

			for (int i = 0; i < 20; i++) {
				System.out.println("");
			}

			System.out.println(p);
			switch (fin) {

			case 1:
				p.afficherRobotsJ2();
				System.out.println("Joueur 2 a gagne !");
				break;

			case 0:
				p.afficherRobotsJ1();
				System.out.println("Joueur 1 a gagne !");
				break;

			case -1:
				System.out.println("Match nul !");
				break;
			}
		}
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
				return (r.getEnergie() >= Constantes.getCoutDeplacementTireur() || r
						.getEnergie() >= Constantes.getCoutTirTireur());
			}
			if (r instanceof Char) {
				return r.getEnergie() >= Constantes.getCoutDeplacementChar()
						|| r.getEnergie() >= Constantes.getCoutTirChar();
			}
			if (r instanceof Piegeur) {
				return r.getEnergie() >= Constantes.getCoutDeplacementPiegeur()
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
					posTemp = new Position(cible.getPosition().getX() + i, r
							.getPosition().getY());
				}
				strTemp = p.posToString(posTemp);
				posTemp = p.getCarte().get(strTemp);
				if (posTemp.estObstacle() || posTemp.estRobot()) {
					return false;
				}
			}
			return true;
		}

		else { // si ni les X, ni les Y sont egaux alors les robots ne sont pas
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
					tmp = p.getCarte().get(
							p.posToString(new Position(r.getPosition().getX()
									+ i, r.getPosition().getY() + j)));
					if (tmp != null) {
						if ((tmp.estBase() && tmp.getEquipe() == r.getEquipe() && checkTotalRobots(r))
								|| !(tmp.estObstacle() || tmp.estRobot() || tmp
										.estBase())) {
							return true;
						}
					}
				}
			}
		} else {
			tmp = p.getCarte().get(
					p.posToString(new Position(r.getPosition().getX() + 1, r
							.getPosition().getY())));
			if (tmp != null) {
				if (!(tmp.estObstacle() || tmp.estRobot())) {
					tmp = p.getCarte().get(
							p.posToString(new Position(
									r.getPosition().getX() + 2, r.getPosition()
											.getY())));
					if (tmp != null) {
						if (!(tmp.estObstacle() || tmp.estRobot())) {
							return true;
						}
					}
				}
			}
			tmp = p.getCarte().get(
					p.posToString(new Position(r.getPosition().getX() - 1, r
							.getPosition().getY())));
			if (tmp != null) {
				if (!(tmp.estObstacle() || tmp.estRobot())) {
					tmp = p.getCarte().get(
							p.posToString(new Position(
									r.getPosition().getX() - 2, r.getPosition()
											.getY())));
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
							p.posToString(new Position(r.getPosition().getX(),
									r.getPosition().getY() + 2)));
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
							p.posToString(new Position(r.getPosition().getX(),
									r.getPosition().getY() - 2)));
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
	 * Verifie le nombre total de robots en dehors de la base pour l'equipe du
	 * robot passe en parametre.
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
				p.recharges();
			}

		} catch (Exception e) {
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

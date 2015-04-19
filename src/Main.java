import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Moi
 */
public class Main {
	/**
	 * C'est le main
	 */
	public static void main(String[] args) {
		Random r = new Random();
		List<Action> actions = new ArrayList<Action>();
		int nbTireurJ1;
		int nbPiegeurJ1;
		int nbCharJ1;
		Scanner s = new Scanner(System.in);
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
		int nbTireurJ2;
		int nbCharJ2;
		int nbPiegeurJ2;

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
		int largeur;
		int hauteur;
		do {
			System.out
					.println(" Entrez la taille de la map (largeur, puis hauteur)");
			largeur = s.nextInt();
			hauteur = s.nextInt();
		} while (largeur < 5 && largeur > 26 && hauteur < 5 && hauteur > 26);
		int obstacles;
		do {
			System.out
					.println("\n\nChoisissez un pourcentage d'obstacles (entier entre 0 et 50)");
			obstacles = s.nextInt();
		} while (obstacles <= 0 && obstacles >= 50);

		Plateau p = new Plateau(largeur, hauteur, obstacles);

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
		boolean joueur = r.nextBoolean(); // J1=true, J2=false
		String joueurCourant; // J1 ou J2
		Robot robotChoisi;
		int choixAction;
		Position choixCible;
		Position.setPlateau(p);

		while (true) {
			p = Position.getPlateau();
			System.out.println(p);
			p.afficherRobotsJ1();
			p.afficherRobotsJ2();
			if (joueur) {
				joueurCourant = "J1";
			} else {
				joueurCourant = "J2";
			}
			System.out
					.println("C'est a  "
							+ joueurCourant
							+ " de jouer !\nselectionnez le numero du robot de votre équipe que vous souhaitez utiliser, ainsi que son action");
			do {
				robotChoisi = p.getListeRobot().get(s.nextInt());
			} while ((robotChoisi.getEquipe() == 1 && joueur)
					|| (robotChoisi.getEquipe() == 0 && !joueur));
			if (robotChoisi instanceof Piegeur) {
				System.out.println("\t1- Poser une mine\n\t2- Se deplacer");
			} else {
				System.out.println("\t1- Attaquer\n\t2- Se deplacer");
			}
			choixAction = s.nextInt();
			System.out.println("Ou ?");
			s.nextLine();
			choixCible = p.stringToPos(s.nextLine());
			if (choixAction == 1) {
				actions.add(new Attaque(robotChoisi, choixCible));
				if (robotChoisi instanceof Piegeur) {
					System.out.println("Le robot " + robotChoisi.getId()
							+ " a posé une mine en "
							+ p.posToString(choixCible));
				} else {
					System.out.println("Le robot " + robotChoisi.getId()
							+ " a attaque le robot "
							+ choixCible.getRobot().getId());
				}
			} else {
				try {
					actions.add(new Deplacement(robotChoisi, choixCible));
				} catch (Erreur e) {
					e.printStackTrace();
				}
				System.out.println("Le robot " + robotChoisi.getId()
						+ " s'est deplace en " + p.posToString(choixCible));
			}
			joueur = !joueur;
			p.recharges();
		}
	}

}

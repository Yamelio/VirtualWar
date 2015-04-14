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

		Scanner s = new Scanner(System.in);
		System.out
				.println("Joueur 1, choisissez vos robots(Maximum 5) :\n\tTireur : ");
		int nbTireurJ1 = s.nextInt();
		System.out.println("\n\tChar : ");
		int nbCharJ1 = s.nextInt();
		System.out.println("\n\tPiegeur : ");
		int nbPiegeurJ1 = s.nextInt();

		System.out
				.println("\n\nJoueur 2, choisissez vos robots(Maximum 5) :\n\tTireur : ");
		int nbTireurJ2 = s.nextInt();
		System.out.println("\n\tChar : ");
		int nbCharJ2 = s.nextInt();
		System.out.println("\n\tPiegeur : ");
		int nbPiegeurJ2 = s.nextInt();

		System.out.println("Taille de la map (largeur, puis hauteur)");

		int largeur = s.nextInt();
		int hauteur = s.nextInt();

		System.out
				.println("\n\nChoisissez un pourcentage d'obstacles (entier entre 1 et 50)");

		int obstacles = s.nextInt();

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

		while (true) {
			System.out.println(p);
			p.afficherRobotsJ1();
			p.afficherRobotsJ2();
			if (joueur) {
				joueurCourant = "J1";
			} else {
				joueurCourant = "J2";
			}
			System.out
					.println("C'est à "
							+ joueurCourant
							+ " de jouer !\nSélectionnez le numéro du robot que vous souhaitez utiliser, ainsi que son action");
			robotChoisi = p.getListeRobot().get(s.nextInt());
			if (robotChoisi instanceof Piegeur) {
				System.out.println("\t1- Poser une mine\n\t2- Se déplacer");
			} else {
				System.out.println("\t1- Attaquer\n\t2- Se déplacer");
			}
			choixAction = s.nextInt();
			System.out.println("Où ?");
			s.nextLine();
			choixCible = p.stringToPos(s.nextLine());
			if (choixAction == 1) {
				actions.add(new Attaque(robotChoisi, choixCible));
				System.out.println("Le robot " + robotChoisi.getId()
						+ " a attaqué le robot "
						+ choixCible.getRobot().getId());
			} else {
				actions.add(new Deplacement(robotChoisi, choixCible));
				System.out.println("Le robot " + robotChoisi.getId()
						+ " s'est déplacé en " + p.posToString(choixCible));
			}
			joueur = !joueur;
		}
	}
}

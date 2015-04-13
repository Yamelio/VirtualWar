/**
 * @author Moi
 */

public class Main {

	/**
	 * C'est le main
	 */
	public static void main(String[] args) {

		Plateau p = new Plateau(5, 5);

		p.ajouterListeRobot(new Tireur(0));
		p.ajouterListeRobot(new Tireur(0));
		p.ajouterListeRobot(new Char(0));
		p.ajouterListeRobot(new Char(0));
		p.ajouterListeRobot(new Piegeur(0));
		p.ajouterListeRobot(new Tireur(1));
		p.ajouterListeRobot(new Tireur(1));
		p.ajouterListeRobot(new Char(1));
		p.ajouterListeRobot(new Char(1));
		p.ajouterListeRobot(new Piegeur(1));

		new Deplacement(p.getListeRobot().get(1), p.getCarte().get("B1"));
		System.out.println(p);

	}
}

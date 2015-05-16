/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse
 * Cette classe représente un objet vue définit par son plateau et son équipe
 *
 */

public class Vue {

	private Plateau plateau;
	private int equipe;

	/**
	 * Contructeur de la classe Vue
	 * 
	 * @param plateau
	 *            Plateau a mettre dans la vue
	 * @param equipe
	 *            equipe correspondante a la vue
	 */
	public Vue(Plateau plateau, int equipe) {
		this.plateau = plateau;
		this.equipe = equipe;
	}

	/**
	 * Verifie si la coordonnee donnee sont libre
	 * 
	 * @param coord
	 *            Coordonnee a verifier
	 * @return true si libre
	 */
	public boolean estLibre(Position p) {
		return !p.estObstacle() && !p.estMine() && !p.estBase();
	}

	/**
	 * 
	 * @return le plateau de la vue
	 */
	public Plateau getPlateau() {
		return this.plateau;
	}

	public int getEquipe() {
		return this.equipe;
	}

	public String toString() {
		String s = "\t";
		for (int k = 0; k < plateau.getLargeur(); k++) {
			s += " " + (char) ((int) 'A' + k) + " ";
		}
		s += "\n";

		for (int i = 1; i <= plateau.getHauteur(); i++) {
			s += "\t";
			for (int j = 1; j <= plateau.getLargeur(); j++) {
				s += "+--";
			}
			s += "+" + "\n" + i + "\t";
			for (int k = 1; k <= plateau.getLargeur(); k++) {
				if (plateau
						.getCarte()
						.get(plateau.posToString((Position) new Position(k, i)))
						.estMine()
						&& plateau
								.getCarte()
								.get(plateau
										.posToString((Position) new Position(k,
												i))).getEquipe() != equipe) {
					s += "|  ";
				} else {
					s += "|"
							+ plateau
									.getCarte()
									.get(plateau
											.posToString((Position) new Position(
													k, i))).getContenu();
				}
			}
			s += "|\n";
		}
		s += "\t";
		for (int j = 0; j < plateau.getLargeur(); j++) {
			s += "+--";
		}
		s += "+";

		return s;
	}

}

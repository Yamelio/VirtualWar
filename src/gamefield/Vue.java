package virtualwars.gamefield;

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
	public boolean estLibre(Coordonnees coord) {
		return !this.plateau.estMur(coord)
				&& this.plateau.contenu(coord) == null
				&& (this.plateau.contientMine(coord) == 0 || this.plateau
						.contientMine(coord) != equipe);
	}

	/**
	 * 
	 * @return le plateau de la vue
	 */
	public Plateau getPlateau() {
		return this.plateau;
	}

}

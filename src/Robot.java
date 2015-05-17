/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe repr�sente un objet
 *         Robot d�finit par son �quipe
 */

public abstract class Robot {

	/** L'�nergie du robot */
	private int energie;

	/** l'�quipe du robot */
	private int equipe;

	/** la position du robot */
	private Position pos;

	/** le compteur servant � d�finir l'ID du robot */
	public static int cpt = 0;

	/** l'ID du robot */
	private int id;

	/**
	 * Construit un Robot � partir de son �quipe
	 * 
	 * @param equipe
	 *            l'�quipe du robot
	 */
	public Robot(int equipe) {
		this.equipe = equipe;
		this.id = cpt++;
		if (equipe == 0) {
			setPosition(Position.getPlateau().getCarte().get("A1"));
		} else {
			setPosition(Position
					.getPlateau()
					.getCarte()
					.get(Position.getPlateau().posToString(
							new Position(Position.getPlateau().getLargeur(),
									Position.getPlateau().getHauteur()))));
		}
	}

	public int getEnergie() {
		return this.energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public int getEquipe() {
		return this.equipe;
	}

	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}

	public Position getPosition() {
		return this.pos;
	}

	public void setPosition(Position pos) {
		this.pos = pos;

	}

	/**
	 * Recharge en vie le robot
	 */
	public void recharger() {
		this.energie += Constantes.getRecharge();
	}

	public int getId() {
		return this.id;
	}

	public abstract int getNbMines();

	public abstract void setNbMines(int nbMines);

	public abstract String toString();

}

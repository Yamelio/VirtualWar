/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe represente un objet
 *         Robot definit par son equipe
 */

public abstract class Robot {

	/** L'energie du robot */
	private int energie;

	/** l'equipe du robot */
	private int equipe;

	/** la position du robot */
	private Position pos;

	/** le compteur servant a definir l'ID du robot */
	public static int cpt = 0;

	/** l'ID du robot */
	private int id;

	/**
	 * Construit un Robot a partir de son equipe
	 * 
	 * @param equipe
	 *            l'equipe du robot
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

	public int getEnergieMax() {
		if (this instanceof Piegeur) {
			return Constantes.getEnergieInitialePiegeur();
		}
		if (this instanceof Char) {
			return Constantes.getEnergieInitialeChar();
		}
		if (this instanceof Tireur) {
			return Constantes.getEnergieInitialeTireur();
		}
		return 0;
	}

	public String getDescription(boolean estReduite) {
		String type = "";
		if (this instanceof Piegeur) {
			type = "Piegeur";
		}
		if (this instanceof Char) {
			type = "Char";
		}
		if (this instanceof Tireur) {
			type = "Tireur";
		}
		if (estReduite) {
			if (!(this instanceof Piegeur)) {
				return type + " " + getId();

			} else {
				return type + " " + getId() + " | Mines : " + getNbMines() + "/"
						+ Constantes.getMinesInit();
			}
		} else {
			if (!(this instanceof Piegeur)) {
				return type + " " + getId() + " " + getEnergie() + "/"
						+ getEnergieMax();

			} else {
				return type + " " + getId() + " " + getEnergie() + "/"
						+ getEnergieMax() + " | Mines : " + this.getNbMines()
						+ "/" + Constantes.getMinesInit();
			}
		}
	}

}

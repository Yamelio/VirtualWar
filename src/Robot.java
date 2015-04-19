public abstract class Robot {

	private int energie;
	private int equipe;
	private Position pos;
	public static int cpt = 0;
	private int id;
	
	/**
	 * Constructeur de la classe robot
	 * 
	 * @param equipe
	 * 		Prend le numéro de léquipe en paramètre
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
	 *Procedure qui recharge l'énérgie du robot courant
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

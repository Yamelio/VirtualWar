/**
 * @author Nicolas Mauger lol
 */

public class Position {

	private int x;
	private int y;
	private int equipe = 2;
	private boolean base = false;
	private boolean mine = false;
	private boolean obstacle = false;
	private static Plateau p;

	/**
	 * Constructeur de la classe Plateau
	 * 
	 * @param x
	 *            Coordonnée de la position en X
	 * @param y
	 *            Coordonnée de la position en y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean estBase() {
		return this.base;
	}

	public boolean estMine() {
		return this.mine;
	}

	public boolean estObstacle() {
		return this.obstacle;
	}

	/**
	 * Fonction qui change en base la position si elle n'est pas base, et
	 * inversement
	 * 
	 * @param equipe
	 *            L'équipe à qui appartient la base
	 */
	public void flipBase(int equipe) {
		if (!this.base) {
			this.base = !this.base;
			this.setEquipe(equipe);
		} else {
			this.base = !this.base;
			this.setEquipe(2);
		}
	}

	/**
	 * Fonction qui change en mine la position si elle n'est pas mine, et
	 * inversement
	 * 
	 * @param equipe
	 *            L'équipe à qui appartient la mine
	 */
	public void flipMine(int equipe) {
		this.mine = !this.mine;
		if (!this.mine) {
			this.setEquipe(equipe);
		} else {
			this.setEquipe(2);
		}
	}

	/**
	 * Procedure qui change la position en obstacle si elle ne l'est pas, et
	 * inversement
	 */
	public void flipObstacle() {
		this.obstacle = !this.obstacle;
	}

	/**
	 * Fonction qui indique si la position est robot ou non
	 * 
	 * @return boolean Retourne true si la position contient un robot
	 */
	public boolean estRobot() {
		for (int i = 0; i < p.getListeRobot().size(); i++) {
			if (p.getListeRobot().get(i).getPosition().getX() == this.getX()
					&& p.getListeRobot().get(i).getPosition().getY() == this
							.getY()) {
				if (!base) {
					return true;
				}
			}
		}
		return false;
	}

	public Robot getRobot() {
		for (int i = 0; i < p.getListeRobot().size(); i++) {
			if (p.getListeRobot().get(i).getPosition().equals(this))
				return p.getListeRobot().get(i);
		}
		return null;

	}

	public static void setPlateau(Plateau pl) {
		p = pl;
	}

	/**
	 * Fonction qui retourne le contenu de la position en ascii art
	 * 
	 * @return String B si c'est une base, M si c'est une mine, # si c'est un
	 *         obstacle
	 */
	public String getContenu() {
		if (this.estBase()) {
			return "B ";
		} else if (this.estMine()) {
			return "M ";
		} else if (this.estObstacle()) {
			return "# ";
		} else if (this.estRobot()) {
			for (int i = 0; i < p.getListeRobot().size(); i++) {
				if (p.getListeRobot().get(i).getPosition().equals(this)) {
					return p.getListeRobot().get(i).toString();
				}
			}
		}
		return "  ";
	}

	public int getEquipe() {
		return equipe;
	}

	public static Plateau getPlateau() {
		return p;
	}

	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}

	public boolean equals(Position p) {
		return x == p.x && y == p.y;

	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

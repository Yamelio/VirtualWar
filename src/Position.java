/**
 * @author Matthieu Gaillard
 * Cette classe représente un objet Position définit à partir de son abscisse x, et de son ordonnée y
 */

public class Position {

	/** L'abscisse de la position */
	private int x;
	
	/** L'ordonnée de la position */
	private int y;
	
	/* L'équipe d'une position, si cette case est une base ou une mine */
	private int equipe = 2;
	
	/** Si la position est une base */
	private boolean base = false;
	
	/** Si la position est une mine */
	private boolean mine = false;
	
	/** Si la position est un obstacle */
	private boolean obstacle = false;

	/** Le plateau du jeu en cours regroupant toutes les Position */
	private static Plateau p;

	/**
	 * Constructeur de la classe Position
	 * 
	 * @param x
	 *            CoordonnÃ©e de la position en X
	 * @param y
	 *            CoordonnÃ©e de la position en y
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

	/*
	 * Fonction qui indique si la position est une base ou non
	 */
	public boolean estBase() {
		return this.base;
	}

	/*
	 * Fonction qui indique si la position est une mine ou non
	 */
	public boolean estMine() {
		return this.mine;
	}

	/*
	 * Fonction qui indique si la position est un obstacle ou non
	 */
	public boolean estObstacle() {
		return this.obstacle;
	}

	/**
	 * Fonction qui change en base la position si elle n'est pas base, et
	 * inversement
	 * 
	 * @param equipe
	 *            L'Ã©quipe Ã  qui appartient la base
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
	 *            L'Ã©quipe Ã  qui appartient la mine
	 */
	public void flipMine(int equipe) {
		if (!this.mine) {
			this.setEquipe(equipe);
		} else {
			this.setEquipe(2);
		}
		this.mine = !this.mine;
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

	
	/**
	 * Retourne le robot présent sur la position, si elle en contient un
	 * @return le robot présent sur la position, si elle en contient un
	 */
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

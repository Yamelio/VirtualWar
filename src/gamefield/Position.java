public class Position {

	private int x;
	private int y;
	private int equipe = 0;
	private boolean base = false;
	private boolean mine = false;
	private boolean obstacle = false;

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

	public boolean isBase() {
		return this.base;
	}

	public boolean isMine() {
		return this.mine;
	}

	public boolean isObstacle() {
		return this.obstacle;
	}

	public void estMine() {
		this.mine = true;
	}

	public void estBase() {
		this.base = true;
	}

	public void estObstacle() {
		this.base = true;
	}

	public void flipBase(int equipe) {
		if (!this.base) {
			this.base = !this.base;
			this.equipe = equipe;
		} else {
			this.base = !this.base;
			this.equipe = 0;
		}
	}

	public void flipMine(int equipe) {
		if (!this.mine) {
			this.mine = !this.mine;
			this.equipe = equipe;
		} else {
			this.mine = !this.mine;
			this.equipe = 0;
		}
	}

	public void flipObstacle() {
		this.obstacle = !this.obstacle;
	}

	/**
	 * Affiche le contenu de la position
	 * 
	 * @return char Retourne le contenu de la cellule en ASCII art
	 */
	public String toString() { // Il faut rajouter les affichages des robots, il
								// n'y que les obstacles, mines, et bases
		String c;
		if (equipe == 1) {
			if (this.mine) {
				c = "M";
			} else if (this.base) {
				c = "B";
			} else {
				c = "E";
			}

		} else if (equipe == 2) {
			if (this.mine) {
				c = "m";
			} else if (this.base) {
				c = "b";
			} else {
				c = "e";
			}

		} else {
			if (this.obstacle) {
				c = "#";
			} else {
				c = " ";
			}
		}
		return c;
	}
}

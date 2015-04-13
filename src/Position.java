public class Position {

	private int x;
	private int y;
	private int equipe = 0;
	private boolean base = false;
	private boolean mine = false;
	private boolean obstacle = false;
	private static Plateau p;

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

	public void flipBase(int equipe) {
		if (!this.base) {
			this.base = !this.base;
			this.setEquipe(equipe);
		} else {
			this.base = !this.base;
			this.setEquipe(0);
		}
	}

	public void flipMine(int equipe) {
		if (!this.mine) {
			this.mine = !this.mine;
			this.setEquipe(equipe);
		} else {
			this.mine = !this.mine;
			this.setEquipe(0);
		}
	}

	public void flipObstacle() {
		this.obstacle = !this.obstacle;
	}

	public boolean estRobot() {
		for (int i = 0; i < p.getListeRobot().size(); i++) {
			if (p.getListeRobot().get(i).getPosition().equals(this))
				return true;
		}
		return false;
	}
	
	public Robot getRobot(){
		
		for (int i = 0; i < p.getListeRobot().size(); i++) {
			if (p.getListeRobot().get(i).getPosition().equals(this))
				return p.getListeRobot().get(i);
		}
		return null;
		
	}
	
	
	public static void setPlateau(Plateau pl) {
		p = pl;
	}

	public String toString() {
		if (this.estBase()) {
			return "B";
		} else if (this.estMine()) {
			return "M";
		} else if (this.estObstacle()) {
			return "#";
		} else if (this.estRobot()) {
			for (int i = 0; i < p.getListeRobot().size(); i++) {
				if (p.getListeRobot().get(i).getPosition().equals(this)) {
					return p.getListeRobot().get(i).toString();
				}
			}
		}
		return " ";
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
}

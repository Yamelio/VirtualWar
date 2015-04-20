import java.util.ArrayList;

public class Base extends Position {
	private ArrayList<Robot> robots;

	/**
	 * Constructeur de Base
	 * 
	 * @param largeur
	 *            SpÃ©cifie la largeur du plateau
	 * @param hauteur
	 *            SpÃ©cifie la hauteur du plateau
	 * @param equipe
	 *            SpÃ©cifie le numÃ©ro d'Ã©quipe de la Base
	 */
	public Base(int largeur, int hauteur, int equipe) {
		super(largeur, hauteur);
		robots = new ArrayList<Robot>();
		flipBase(equipe);
	}

	/**
	 * Procedure qui déplace le robot pris en paramètre dans la base
	 * 
	 * @param robot
	 *            Le robot qui doit être déplacer sur la base
	 */
	public void deplaceSur(Robot robot) {
		robots.add(robot);
	}

	/**
	 * Retire le robot prit en paramétre de la base
	 * 
	 * @param robot
	 *            Le robot qui doit être retirer
	 */
	public void quitteBase(Robot robot) {
		robots.remove(robot);
	}

	/**
	 * Procedure qui retire tout les robots présent dans la base
	 */
	public void videCase() {
		ArrayList<Robot> toRemove = new ArrayList<Robot>();
		for (Robot r : robots) {
			if (!r.getPosition().equals(new Position(getX(), getY())))
				;
			toRemove.add(r);
		}
		for (Robot t : toRemove) {
			robots.remove(t);
		}
	}

	public ArrayList<Robot> getRobotsInBase() {
		return robots;
	}

	public String toString() {
		return ("Equipe " + getEquipe() + ", contient " + robots);
	}

	/**
	 * Procédure qui recharge en énergie les robots présent dans la base, et
	 * remplie le stock de mine si c'est un piègeur
	 */
	public void recharge() {
		for (Robot r : robots) {
			r.recharger();
			if (r instanceof Piegeur) {
				if (r.getEnergie() > Constantes.getEnergieInitialePiegeur()) {
					r.setEnergie(Constantes.getEnergieInitialePiegeur());
				}
			} else {
				if (r instanceof Char) {
					if (r.getEnergie() > Constantes.getEnergieInitialeChar()) {
						r.setEnergie(Constantes.getEnergieInitialeChar());
					}
				} else {
					if (r.getEnergie() > Constantes.getEnergieInitialeTireur()) {
						r.setEnergie(Constantes.getEnergieInitialeTireur());
					}
				}
			}
		}
	}
}

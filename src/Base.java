import java.util.ArrayList;

public class Base extends Position {
	private ArrayList<Robot> robots;

	/**
	 * Constructeur de Base
	 * 
	 * @param largeur
	 *            Spécifie la largeur du plateau
	 * @param hauteur
	 *            Spécifie la hauteur du plateau
	 * @param equipe
	 *            Spécifie le numéro d'équipe de la Base
	 */
	public Base(int largeur, int hauteur, int equipe) {
		super(largeur, hauteur);
		robots = new ArrayList<Robot>();
		flipBase(equipe);
	}

	/**
	 * Procedure qui d�place le robot pris en param�tre dans la base
	 * 
	 * @param robot
	 *            Le robot qui doit �tre d�placer sur la base
	 */
	public void deplaceSur(Robot robot) {
		robots.add(robot);
	}

	/**
	 * Retire le robot prit en param�tre de la base
	 * 
	 * @param robot
	 *            Le robot qui doit �tre retirer
	 */
	public void quitteBase(Robot robot) {
		robots.remove(robot);
	}

	/**
	 * Procedure qui retire tout les robots pr�sent dans la base
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
	 * Proc�dure qui recharge en �nergie les robots pr�sent dans la base, et
	 * remplie le stock de mine si c'est un pi�geur
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

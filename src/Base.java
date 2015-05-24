/**
 * 
 * @author Nicolas Mauger
 * Cette classe represente un objet Base definit par sa position et son equipe
 */

import java.util.ArrayList;

public class Base extends Position {

	/** La liste des robots presents en base */
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
	 * Procedure qui deplace le robot pris en parametre dans la base
	 * 
	 * @param robot
	 *            Le robot qui doit etre deplacer sur la base
	 */
	public void deplaceSur(Robot robot) {
		robots.add(robot);
	}

	/**
	 * Retire le robot prit en parametre de la base
	 * 
	 * @param robot
	 *            Le robot qui doit etre retirer
	 */
	public void quitteBase(Robot robot) {
		robots.remove(robot);
	}

	/**
	 * Procedure qui retire tout les robots present dans la base
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

	/**
	 * Retourne la liste des robots presents en base
	 * 
	 * @return la liste des robots presents en base
	 */
	public ArrayList<Robot> getRobotsInBase() {
		return robots;
	}

	public String toString() {
		return ("Equipe " + getEquipe() + ", contient " + robots);
	}

	/**
	 * Procedure qui recharge en energie les robots present dans la base, et
	 * remplie le stock de mine si c'est un piegeur
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

	public String getDescriptionRobots() {
		String res = "/// ";
		for (Robot r : robots) {
			res +=(r.getDescription(false))+ " /// ";
		}
		return res;
	}
}

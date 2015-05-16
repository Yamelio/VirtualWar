/**
 * 
 * @author Matthieu Gaillard
 * Cette classe abstraite représente un objet action définit par le robot qui réalise cette action et la cible
 *
 */
public abstract class Action {

	//* Le Robot */
	private Robot robot;
	
	/** la cible */
	protected Position cible;
	
	/**
	 * Construit un objet action en fonction de son robot et de sa cible
	 * @param robot le robot qui réalise l'action
	 * @param cible la cible de cette action
	 */
	public Action(Robot robot, Position cible) {
		this.robot = robot;
		this.cible = cible;
	}

	/**
	 * Retourne le robot concerné par l'action
	 * @return le robot concerné par l'action
	 */
	public Robot getRobot() {
		return this.robot;
	}
	
	/**
	 * Retourne la cible de cette action
	 * @return la cible de cette action
	 */
	public Position getCible() {
		return this.cible;
	}
}

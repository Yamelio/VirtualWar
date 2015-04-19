/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un objet Action, d�finit par son robot
 */
public abstract class Action {

	/** Robot concern� par l'Action */
	private Robot robot;
	
	/**
	 * 
	 * Constructeur de la classe Action
	 * 
	 * @param robot
	 * 					robot qui agit
	 */
	public Action(Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return this.robot;
	}
}

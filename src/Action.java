/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un objet Action, définit par son robot
 */
public abstract class Action {

	/** Robot concerné par l'Action */
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

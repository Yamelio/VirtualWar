/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un objet Action, définit par son robot
 */
public abstract class Action {
	
	/** Robot concerné par l'Action*/
	private Robot robot;
	
	/**
	 * Construit une Action que va réaliser un robot
	 * @param robot - Le Robot qui va effectuer une action
	 */
	public Action(Robot robot){
		this.robot = robot;
	}
	/**
	 * Retourne le robot concerné par une action
	 * @return le robot concerné par un action
	 */
	public Robot getRobot(){return this.robot;}
}

/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un objet Action, d�finit par son robot
 */
public abstract class Action {
	
	/** Robot concern� par l'Action*/
	private Robot robot;
	
	/**
	 * Construit une Action que va r�aliser un robot
	 * @param robot - Le Robot qui va effectuer une action
	 */
	public Action(Robot robot){
		this.robot = robot;
	}
	/**
	 * Retourne le robot concern� par une action
	 * @return le robot concern� par un action
	 */
	public Robot getRobot(){return this.robot;}
}

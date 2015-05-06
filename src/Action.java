public abstract class Action {

	private Robot robot;
	protected Position cible;

	public Action(Robot robot, Position cible) {
		this.robot = robot;
		this.cible = cible;
	}

	public Robot getRobot() {
		return this.robot;
	}

	public Position getCible() {
		return this.cible;
	}
}

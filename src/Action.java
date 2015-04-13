public abstract class Action {

	private Robot robot;

	public Action(Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return this.robot;
	}
}

public class Deplacement extends Action {

	private Constantes constantes = new Constantes();
	private Position cible;

	public Deplacement(Robot robot, Position cible) {
		super(robot);
		if (this.getRobot() instanceof Tireur
				|| this.getRobot() instanceof Piegeur) {
			if ((this.getRobot().getPosition().getX() == cible.getX()
					- constantes.getPorteeDeplacementTireur()
					|| this.getRobot().getPosition().getX() == cible.getX()
							+ constantes.getPorteeDeplacementTireur() || this
					.getRobot().getPosition().getX() == cible.getX())
					&& (this.getRobot().getPosition().getY() == cible.getY()
							- constantes.getPorteeDeplacementTireur()
							|| this.getRobot().getPosition().getY() == cible
									.getY()
									+ constantes.getPorteeDeplacementTireur() || this
							.getRobot().getPosition().getY() == cible.getY())) {
				if (!cible.estBase() && !cible.estObstacle()
						&& !cible.estRobot()) {
					this.cible = cible;
					this.getRobot().setPosition(this.cible);
				}
			}
		} else {
			if (((this.getRobot().getPosition().getX() == cible.getX()
					- constantes.getPorteeDeplacementChar() || this.getRobot()
					.getPosition().getX() == cible.getX()
					+ constantes.getPorteeDeplacementChar()) && this.getRobot()
					.getPosition().getY() == cible.getY())
					|| ((this.getRobot().getPosition().getY() == cible.getY()
							- constantes.getPorteeDeplacementChar() || this
							.getRobot().getPosition().getY() == cible.getY()
							+ constantes.getPorteeDeplacementChar()) && this
							.getRobot().getPosition().getX() == cible.getX())) {
				if (!cible.estBase() && !cible.estObstacle()
						&& !cible.estRobot()) {
					this.cible = cible;
					this.getRobot().setPosition(this.cible);
				}
			}

		}
	}

	public Position getCible() {
		return this.cible;
	}

	public void setCible(Position cible2) {
		this.cible = cible2;
	} // si la cible initiale ne convient pas
}

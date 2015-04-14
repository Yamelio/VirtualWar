public class Deplacement extends Action {

	private Position cible;

	public Deplacement(Robot robot, Position cible) {
		super(robot);
		if (this.getRobot() instanceof Tireur
				|| this.getRobot() instanceof Piegeur) {
			if ((this.getRobot().getPosition().getX() == cible.getX()
					- Constantes.getPorteeDeplacementTireur()
					|| this.getRobot().getPosition().getX() == cible.getX()
							+ Constantes.getPorteeDeplacementTireur() || this
					.getRobot().getPosition().getX() == cible.getX())
					&& (this.getRobot().getPosition().getY() == cible.getY()
							- Constantes.getPorteeDeplacementTireur()
							|| this.getRobot().getPosition().getY() == cible
									.getY()
									+ Constantes.getPorteeDeplacementTireur() || this
							.getRobot().getPosition().getY() == cible.getY())) {
				if (!cible.estBase() && !cible.estObstacle()
						&& !cible.estRobot()) {
					quitteBase(robot.getPosition());
					this.cible = cible;
					this.getRobot().setPosition(this.cible);
					if (this.getRobot() instanceof Tireur) {
						this.getRobot()
								.setEnergie(
										this.getRobot().getEnergie()
												- Constantes
														.getCoutDeplacementTireur());
					} else if (this.getRobot() instanceof Piegeur) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes
												.getCoutDeplacementPiegeur());
					}
					if (this.cible.estMine()
							&& this.getRobot() instanceof Tireur) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getDegatsTireur());
					} else if (this.cible.estMine()
							&& this.getRobot() instanceof Piegeur) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getDegatsPiegeur());
					}
				}
			}
		} else {
			if (((this.getRobot().getPosition().getX() == cible.getX()
					- Constantes.getPorteeDeplacementChar() || this.getRobot()
					.getPosition().getX() == cible.getX()
					+ Constantes.getPorteeDeplacementChar()) && this.getRobot()
					.getPosition().getY() == cible.getY())
					|| ((this.getRobot().getPosition().getY() == cible.getY()
							- Constantes.getPorteeDeplacementChar() || this
							.getRobot().getPosition().getY() == cible.getY()
							+ Constantes.getPorteeDeplacementChar()) && this
							.getRobot().getPosition().getX() == cible.getX())) {
				if (!cible.estBase() && !cible.estObstacle()
						&& !cible.estRobot()) {
					quitteBase(robot.getPosition());
					this.cible = cible;
					this.getRobot().setPosition(this.cible);
					this.getRobot().setEnergie(
							this.getRobot().getEnergie()
									- Constantes.getCoutDeplacementChar());
					if (this.cible.estMine()) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getDegatsChar());
					}

				} else if (cible.estObstacle() || cible.estRobot()) {
					if (this.getRobot().getPosition().getX() == this.cible
							.getX()) {
						if (this.getRobot().getPosition().getY()
								- this.cible.getY() < 0) {
							quitteBase(robot.getPosition());
							this.cible = new Position(this.cible.getX(),
									this.cible.getY() - 1);
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						} else {
							quitteBase(robot.getPosition());
							this.cible = new Position(this.cible.getX(),
									this.cible.getY() + 1);
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						}
					} else if (this.getRobot().getPosition().getY() == cible
							.getY()) {
						if (this.getRobot().getPosition().getX()
								- this.cible.getX() < 0) {
							quitteBase(robot.getPosition());
							this.cible = new Position(this.cible.getX() - 1,
									this.cible.getY());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						} else {
							quitteBase(robot.getPosition());
							this.cible = new Position(this.cible.getX() + 1,
									this.cible.getY());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						}
					}
				}
			}

		}
	}

	public Position getCible() {
		return this.cible;
	}

	public void quitteBase(Position p) {
		if (p.getRobot().getPosition().estBase()) {
			((Base) p.getRobot().getPosition()).quitteBase(p.getRobot());
		}
	}
}

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
					quitteBase(robot);
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
						Position.getPlateau().getCarte()
						.get(Position.getPlateau().posToString(cible))
						.flipMine(0);
					} else if (this.cible.estMine()
							&& this.getRobot() instanceof Piegeur) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getDegatsPiegeur());
						Position.getPlateau().getCarte()
						.get(Position.getPlateau().posToString(cible))
						.flipMine(0);
					}
				}else if(cible.estBase()){
					if(cible.getEquipe() == robot.getEquipe()){
						if(robot.getEquipe() == 0){
							this.cible = cible;
							((Base) Position.getPlateau().getCarte().get("A1")).deplaceSur(this.getRobot());
							this.getRobot().setPosition(this.cible);
							if(this.getRobot() instanceof Tireur){
								this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementTireur());
							}else if(this.getRobot() instanceof Piegeur){
								this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementPiegeur());
							}
						}else {
							this.cible = cible;
							((Base) Position.getPlateau().getCarte().get(Position.getPlateau()
							.posToString(new Position(Position.getPlateau().getLargeur(),
							Position.getPlateau().getHauteur()))))
							.deplaceSur(this.getRobot());
							this.getRobot().setPosition(this.cible);
							if(this.getRobot() instanceof Tireur){
								this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementTireur());
							}else if(this.getRobot() instanceof Piegeur){
								this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementPiegeur());
							}
						}
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
					quitteBase(robot);
					this.cible = cible;
					this.getRobot().setPosition(this.cible);
					this.getRobot().setEnergie(
							this.getRobot().getEnergie()
									- Constantes.getCoutDeplacementChar());
					if (this.cible.estMine()) {
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getDegatsChar());
						Position.getPlateau().getCarte()
						.get(Position.getPlateau().posToString(cible))
						.flipMine(0);
					}

				} else if (cible.estObstacle() || cible.estRobot()) {
					if (this.getRobot().getPosition().getX() == this.cible
							.getX()) {
						if (this.getRobot().getPosition().getY()
								- this.cible.getY() < 0) {
							quitteBase(robot);
							this.cible.setPosition(this.cible.getX(),
									this.cible.getY() - 1);
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						} else {
							quitteBase(robot);
							this.cible.setPosition(this.cible.getX(),
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
							quitteBase(robot);
							this.cible.setPosition(this.cible.getX() - 1,
									this.cible.getY());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						} else {
							quitteBase(robot);
							this.cible.setPosition(this.cible.getX() + 1,
									this.cible.getY());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes
													.getCoutDeplacementChar());
						}
					}
				}else if(cible.estBase()){
					if(cible.getEquipe() == robot.getEquipe()){
						if(robot.getEquipe() == 0){
							this.cible = cible;
							((Base) Position.getPlateau().getCarte().get("A1")).deplaceSur(this.getRobot());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementChar());
						}else {
							this.cible = cible;
							((Base) Position.getPlateau().getCarte().get(Position.getPlateau()
							.posToString(new Position(Position.getPlateau().getLargeur(),
							Position.getPlateau().getHauteur()))))
							.deplaceSur(this.getRobot());
							this.getRobot().setPosition(this.cible);
							this.getRobot().setEnergie(this.getRobot().getEnergie() - Constantes.getCoutDeplacementChar());
						}
					}
				}
			}

		}
	}

	public Position getCible() {
		return this.cible;
	}

	public void quitteBase(Robot r) {
		if (r.getPosition().estBase()) {
			((Base) r.getPosition()).quitteBase(r);
		}
	}
}

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
					if(this.cible.estMine() && this.getRobot() instanceof Tireur){
						this.getRobot().setEnergie(this.getRobot().getEnergie() - constantes.getDegatsTireur());
					}else if(this.cible.estMine() && this.getRobot() instanceof Piegeur){
						this.getRobot().setEnergie(this.getRobot().getEnergie() - constantes.getDegatsPiegeur());
					}
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
					if(this.cible.estMine()){
						this.getRobot().setEnergie(this.getRobot().getEnergie() - constantes.getDegatsChar());
					}
						
				}else if(cible.estObstacle() || cible.estRobot()){
					if(this.getRobot().getPosition().getX() == this.cible.getX()){
						if(this.getRobot().getPosition().getY() - this.cible.getY() < 0){
							this.cible = new Position(this.cible.getX(), this.cible.getY() - 1); 
							this.getRobot().setPosition(this.cible);
						}else {
							this.cible = new Position(this.cible.getX(), this.cible.getY() + 1);
						}
					}else if(this.getRobot().getPosition().getY() == cible.getY()){
						if(this.getRobot().getPosition().getX() - this.cible.getX() < 0){
							this.cible = new Position(this.cible.getX() - 1, this.cible.getY()); 
							this.getRobot().setPosition(this.cible);
						}else {
							this.cible = new Position(this.cible.getX() + 1, this.cible.getY());
						}
					}
				}
			}

		}
	}

	public Position getCible() {
		return this.cible;
	}
}

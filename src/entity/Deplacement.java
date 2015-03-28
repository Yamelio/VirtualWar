
public class Deplacement {

	private Constantes constantes = new Constantes();
	private Robot robot;
	private Position cible;
	
	public Deplacement(Robot robot, Position cible){
		this.robot = robot;
		if(this.robot instanceof Tireur || this.robot instanceof Piegeur){
			if((this.robot.getPosition().getX() == cible.getX() - constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getX() == cible.getX() + constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getX() == cible.getX())
			&& (this.robot.getPosition().getY() == cible.getY() - constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getY() == cible.getY() + constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getY() == cible.getY())) {
				this.cible = cible;
				this.robot.setPosition(this.cible);
			}
		}else {
			if(((this.robot.getPosition().getX() == cible.getX() - constantes.getPorteeDeplacementChar()
			|| this.robot.getPosition().getX() == cible.getX() + constantes.getPorteeDeplacementChar())
			&& this.robot.getPosition().getY() == cible.getY())
			||((this.robot.getPosition().getY() == cible.getY() - constantes.getPorteeDeplacementChar()
			||this.robot.getPosition().getY() == cible.getY() + constantes.getPorteeDeplacementChar())
			&& this.robot.getPosition().getX() == cible.getX())) {
				this.cible = cible;
				this.robot.setPosition(this.cible);
			}
			
		}
	}

	public Robot getRobot(){return this.robot;}
	
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas
}
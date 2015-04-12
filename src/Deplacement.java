/**
 * 
 * @author Matthieu Gaillard et Benjamin Holquin
 * Cette classe repr�sente un Objet D�placement � partir de son robot et de sa cible
 */
public class Deplacement extends Action{

	/** Les constantes permettant de d�terminer l'�nergie initiale, la port�e etc... */
	private Constantes constantes = new Constantes();
	
	/** Le robot concern� par le d�placement */
	private Robot robot;
	
	/** La cible ou veut se d�placer le robot */
	private Position cible;
	
	/**
	 * Construit un Deplacement � partir d'un robot et d'une cible en v�rifiant sa possibilit�
	 * @param robot - le robot concern� par le d�placement
	 * @param cible - la cible ou veut se deplacer le robot
	 */
	public Deplacement(Robot robot, Position cible){
		super(robot);
		if(this.robot instanceof Tireur || this.robot instanceof Piegeur){
			if((this.robot.getPosition().getX() == cible.getX() - constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getX() == cible.getX() + constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getX() == cible.getX())
			&& (this.robot.getPosition().getY() == cible.getY() - constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getY() == cible.getY() + constantes.getPorteeDeplacementTireur()
			|| this.robot.getPosition().getY() == cible.getY())) {
				if(!cible.estBase()
				&& !cible.estObstacle()
				&& !cible.estRobot()){
					this.cible = cible;
					this.robot.setPosition(this.cible);
				}
			}
		}else {
			if(((this.robot.getPosition().getX() == cible.getX() - constantes.getPorteeDeplacementChar()
			|| this.robot.getPosition().getX() == cible.getX() + constantes.getPorteeDeplacementChar())
			&& this.robot.getPosition().getY() == cible.getY())
			||((this.robot.getPosition().getY() == cible.getY() - constantes.getPorteeDeplacementChar()
			||this.robot.getPosition().getY() == cible.getY() + constantes.getPorteeDeplacementChar())
			&& this.robot.getPosition().getX() == cible.getX())) {
				if(!cible.estBase()
				&& !cible.estObstacle()
				&& !cible.estRobot()){
				this.cible = cible;
				this.robot.setPosition(this.cible);
				}
			}
			
		}
	}


	/**
	 * Retourne la cible du d�placement
	 * @return la cible du d�placement
	 */
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas
}

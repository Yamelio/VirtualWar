/**
 * 
 * @author Matthieu Gaillard et Benjamin Holquin
 * Cette classe représente un Objet Déplacement à partir de son robot et de sa cible
 */
public class Deplacement extends Action{

	/** Les constantes permettant de déterminer l'énergie initiale, la portée etc... */
	private Constantes constantes = new Constantes();
	
	/** Le robot concerné par le déplacement */
	private Robot robot;
	
	/** La cible ou veut se déplacer le robot */
	private Position cible;
	
	/**
	 * Construit un Deplacement à partir d'un robot et d'une cible en vérifiant sa possibilité
	 * @param robot - le robot concerné par le déplacement
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
	 * Retourne la cible du déplacement
	 * @return la cible du déplacement
	 */
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas
}

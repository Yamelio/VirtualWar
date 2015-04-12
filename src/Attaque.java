/**
 * 
 * @author Matthieu Gaillard et Benjamin Holquin
 * Cette classe représente un objet Attaque, définit avec son robot et sa cible
 */
public class Attaque extends Action{

	/** Les constantes permettant de déterminer l'énergie initiale, la portée etc... */
	private Constantes constantes = new Constantes();
	
	/** Le robot concerné par l'attaque */
	private Robot robot;
	
	/** La cible du robot*/
	private Position cible;
	
	/**
	 * Construit une Attaque à partir d'un robot et de sa cible en vérifiant sa possibilité
	 * @param robot - le robot concerné par l'attaque
	 * @param cible - la cible du robot
	 */
	public Attaque(Robot robot, Position cible){
		super(robot);
		if(this.robot instanceof Tireur){
			if(this.robot.getPosition().getY() == cible.getY()){
				if(this.robot.getPosition().getX() > cible.getX()){
					if((this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteeTireur())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getX() > this.robot.getPosition().getX()){
					if((cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteeTireur())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}
			}else if(this.robot.getPosition().getX() == cible.getX()){
				if(this.robot.getPosition().getY() > cible.getY()){
					if((this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteeTireur())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getY() > this.robot.getPosition().getY()){
						if((cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeTireur())
						&& tireSansEntrave()){
							this.cible = cible;
							this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
						}
				}
			}
		}else if(this.robot instanceof Char){
			if(this.robot.getPosition().getY() == cible.getY()){
				if(this.robot.getPosition().getX() > cible.getX()){
					if((this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteeChar())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getX() > this.robot.getPosition().getX()){
					if((cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteeChar())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}
			}else if(this.robot.getPosition().getX() == cible.getX()){
				if(this.robot.getPosition().getY() > cible.getY()){
					if((this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteeChar())
					&& tireSansEntrave()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getY() > this.robot.getPosition().getY()){
						if((cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeChar())
						&& tireSansEntrave()){
							this.cible = cible;
							this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
						}
				}
			}
		}else{
			if(this.robot.getNbMines() > 0){
				if(this.robot.getPosition().getX() >= cible.getX()
				&& this.robot.getPosition().getY() >= cible.getY()){
					if(this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
					&& this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()
					&& !cible.estBase()
					&& !cible.estObstacle()
					&& !cible.estRobot()
					&& !cible.estMine()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
						this.cible.flipMine(this.robot.getEquipe());
						this.robot.setNbMines(this.robot.getNbMines() - 1);  
					}
				}else if(this.robot.getPosition().getX() >= cible.getX()
				&& this.robot.getPosition().getY() <= cible.getY()){
					if(this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
					&& cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeTireur()
					&& !cible.estBase()
					&& !cible.estObstacle()
					&& !cible.estRobot()
					&& !cible.estMine()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
						this.cible.flipMine(this.robot.getEquipe());
						this.robot.setNbMines(this.robot.getNbMines() - 1);
					}
				}else if(this.robot.getPosition().getX() <= cible.getX()
				&& this.robot.getPosition().getY() >= cible.getY()){
					if(cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteePiegeur()
					&& this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()
					&& !cible.estBase()
					&& !cible.estObstacle()
					&& !cible.estRobot()
					&& !cible.estMine()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
						this.cible.flipMine(this.robot.getEquipe());
						this.robot.setNbMines(this.robot.getNbMines() - 1);
					}
				}else {
					if(cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteePiegeur()
					&& cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteePiegeur()
					&& !cible.estBase()
					&& !cible.estObstacle()
					&& !cible.estRobot()
					&& !cible.estMine()){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
						this.cible.flipMine(this.robot.getEquipe());
						this.robot.setNbMines(this.robot.getNbMines() - 1);
					}
				}
			}
		}
	}
	/**
	 * Retourne un booleen qui est vrai si il y a un obstacle sur la trajectoire du tir, faux si non
	 * @return un booleen qui est vrai si il y a un obstacle sur la trajectoire du tir, faux si non
	 */
	public boolean tireSansEntrave(){
		Position tmp;
		if (cible.getX()==this.robot.getPosition().getX()){
			if(cible.getY()<this.robot.getPosition().getY()){
				for (int v = this.robot.getPosition().getY()+1; v < cible.getY(); v++){
					tmp = new Position(this.robot.getPosition().getX(),v);
							if(tmp.estRobot()
							|| tmp.estBase()
							|| tmp.estObstacle()){
								return false;
					}
				}
			}else {
				for (int v = this.robot.getPosition().getY()-1; v > cible.getY(); v--){
					tmp = new Position(this.robot.getPosition().getX(),v);
							if(tmp.estRobot()
							|| tmp.estBase()
							|| tmp.estObstacle()){
								return false;
							}
				}
			}
		}else if(cible.getY()==this.robot.getPosition().getY()){
			if(cible.getX()<this.robot.getPosition().getX()){
				for (int v = this.robot.getPosition().getX()+1; v < cible.getX(); v++){
					tmp = new Position(v,this.robot.getPosition().getX());
							if(tmp.estRobot()
							|| tmp.estBase()
							|| tmp.estObstacle()){
								return false;
							}
				}
			}else {
				for (int v = this.robot.getPosition().getX()-1; v > cible.getX(); v--){
					tmp = new Position(v,this.robot.getPosition().getX());
							if(tmp.estRobot()
							|| tmp.estBase()
							|| tmp.estObstacle()){
								return false;
							}
				}
			}
		}
		if(cible.getX()!=this.robot.getPosition().getX() 
		&& cible.getY()==this.robot.getPosition().getY()){
			return false;
		}
		return true;
	}
	
	/**
	 * Retourne la cible de l'attaque
	 * @return la cible de l'attaque
	 */
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas

}
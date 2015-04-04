
public class Attaque extends Action{

	private Constantes constantes = new Constantes();
	private Robot robot;
	private Position cible;
		
	public Attaque(Robot robot, Position cible){
		super(robot);
		if(this.robot instanceof Tireur){
			if(this.robot.getPosition().getY() == cible.getY()){
				if(this.robot.getPosition().getX() > cible.getX()){
					if((this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getX() > this.robot.getPosition().getX()){
					if((cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}
			}else if(this.robot.getPosition().getX() == cible.getX()){
				if(this.robot.getPosition().getY() > cible.getY()){
					if((this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getY() > this.robot.getPosition().getY()){
						if((cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeTireur())){
							this.cible = cible;
							this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirTireur());
						}
				}
			}
		}else if(this.robot instanceof Char){
			if(this.robot.getPosition().getY() == cible.getY()){
				if(this.robot.getPosition().getX() > cible.getX()){
					if((this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getX() > this.robot.getPosition().getX()){
					if((cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}
			}else if(this.robot.getPosition().getX() == cible.getX()){
				if(this.robot.getPosition().getY() > cible.getY()){
					if((this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getY() > this.robot.getPosition().getY()){
						if((cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeChar())){
							this.cible = cible;
							this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutTirChar());
						}
				}
			}
		}else{
			if(this.robot.getPosition().getX() >= cible.getX()
			&& this.robot.getPosition().getY() >= cible.getY()){
				if(this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
				&& this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
					cible.flipMine(this.robot.getEquipe());
				}
			}else if(this.robot.getPosition().getX() >= cible.getX()
			&& this.robot.getPosition().getY() <= cible.getY()){
				if(this.robot.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
				&& cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteeTireur()){
					this.cible = cible;
					this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.robot.getEquipe());
				}
			}else if(this.robot.getPosition().getX() <= cible.getX()
			&& this.robot.getPosition().getY() >= cible.getY()){
				if(cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteePiegeur()
				&& this.robot.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.robot.getEquipe());
				}
			}else {
				if(cible.getX() - this.robot.getPosition().getX() <= constantes.getPorteePiegeur()
				&& cible.getY() - this.robot.getPosition().getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.robot.setEnergie(this.robot.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.robot.getEquipe());
				}
			}
		}
	}
	
	
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas

}
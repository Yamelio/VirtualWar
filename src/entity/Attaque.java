
public class Attaque {

	private Constantes constantes = new Constantes();
	private Robot attaquant;
	private Position cible;
		
	public Attaque(Robot robot, Position cible){
		this.attaquant = robot;
		if(this.attaquant instanceof Tireur){
			if(this.attaquant.getPosition().getY() == cible.getY()){
				if(this.attaquant.getPosition().getX() > cible.getX()){
					if((this.attaquant.getPosition().getX() - cible.getX() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getX() > this.attaquant.getPosition().getX()){
					if((cible.getX() - this.attaquant.getPosition().getX() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirTireur());
					}
				}
			}else if(this.attaquant.getPosition().getX() == cible.getX()){
				if(this.attaquant.getPosition().getY() > cible.getY()){
					if((this.attaquant.getPosition().getY() - cible.getY() <= constantes.getPorteeTireur())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirTireur());
					}
				}else if(cible.getY() > this.attaquant.getPosition().getY()){
						if((cible.getY() - this.attaquant.getPosition().getY() <= constantes.getPorteeTireur())){
							this.cible = cible;
							this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirTireur());
						}
				}
			}
		}else if(this.attaquant instanceof Char){
			if(this.attaquant.getPosition().getY() == cible.getY()){
				if(this.attaquant.getPosition().getX() > cible.getX()){
					if((this.attaquant.getPosition().getX() - cible.getX() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getX() > this.attaquant.getPosition().getX()){
					if((cible.getX() - this.attaquant.getPosition().getX() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirChar());
					}
				}
			}else if(this.attaquant.getPosition().getX() == cible.getX()){
				if(this.attaquant.getPosition().getY() > cible.getY()){
					if((this.attaquant.getPosition().getY() - cible.getY() <= constantes.getPorteeChar())){
						this.cible = cible;
						this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirChar());
					}
				}else if(cible.getY() > this.attaquant.getPosition().getY()){
						if((cible.getY() - this.attaquant.getPosition().getY() <= constantes.getPorteeChar())){
							this.cible = cible;
							this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutTirChar());
						}
				}
			}
		}else{
			if(this.attaquant.getPosition().getX() >= cible.getX()
			&& this.attaquant.getPosition().getY() >= cible.getY()){
				if(this.attaquant.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
				&& this.attaquant.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutMine());
					cible.flipMine(this.attaquant.getEquipe());
				}
			}else if(this.attaquant.getPosition().getX() >= cible.getX()
			&& this.attaquant.getPosition().getY() <= cible.getY()){
				if(this.attaquant.getPosition().getX() - cible.getX() <= constantes.getPorteePiegeur()
				&& cible.getY() - this.attaquant.getPosition().getY() <= constantes.getPorteeTireur()){
					this.cible = cible;
					this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.attaquant.getEquipe());
				}
			}else if(this.attaquant.getPosition().getX() <= cible.getX()
			&& this.attaquant.getPosition().getY() >= cible.getY()){
				if(cible.getX() - this.attaquant.getPosition().getX() <= constantes.getPorteePiegeur()
				&& this.attaquant.getPosition().getY() - cible.getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.attaquant.getEquipe());
				}
			}else {
				if(cible.getX() - this.attaquant.getPosition().getX() <= constantes.getPorteePiegeur()
				&& cible.getY() - this.attaquant.getPosition().getY() <= constantes.getPorteePiegeur()){
					this.cible = cible;
					this.attaquant.setEnergie(this.attaquant.getEnergie() - constantes.getCoutMine());
					this.cible.flipMine(this.attaquant.getEquipe());
				}
			}
		}
	}
	
	public Robot getAttaquant(){return this.attaquant;}
	
	public Position getCible(){return this.cible;}
	
	public void setCible(Position cible2){} //si la cible initiale ne convient pas

}
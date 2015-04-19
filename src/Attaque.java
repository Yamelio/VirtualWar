public class Attaque extends Action {

	private Position cible;

	public Attaque(Robot robot, Position cible) throws Erreur {
		super(robot);
		int energieInit=getRobot().getEnergie();
		this.cible = cible;
		if (this.getRobot() instanceof Tireur) {
			if (this.getRobot().getPosition().getY() == cible.getY()) {
				if (this.getRobot().getPosition().getX() > cible.getX()) {
					if ((this.getRobot().getPosition().getX() - cible.getX() <= Constantes
							.getPorteeTireur()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirTireur());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				} else if (cible.getX() > this.getRobot().getPosition().getX()) {
					if ((cible.getX() - this.getRobot().getPosition().getX() <= Constantes
							.getPorteeTireur()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirTireur());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				}
			} else if (this.getRobot().getPosition().getX() == cible.getX()) {
				if (this.getRobot().getPosition().getY() > cible.getY()) {
					if ((this.getRobot().getPosition().getY() - cible.getY() <= Constantes
							.getPorteeTireur()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirTireur());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				} else if (cible.getY() > this.getRobot().getPosition().getY()) {
					if ((cible.getY() - this.getRobot().getPosition().getY() <= Constantes
							.getPorteeTireur()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirTireur());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				}
			}
		} else if (this.getRobot() instanceof Char) {
			if (this.getRobot().getPosition().getY() == cible.getY()) {
				if (this.getRobot().getPosition().getX() > cible.getX()) {
					if ((this.getRobot().getPosition().getX() - cible.getX() <= Constantes
							.getPorteeChar()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirChar());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				} else if (cible.getX() > this.getRobot().getPosition().getX()) {
					if ((cible.getX() - this.getRobot().getPosition().getX() <= Constantes
							.getPorteeChar()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirChar());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				}
			} else if (this.getRobot().getPosition().getX() == cible.getX()) {
				if (this.getRobot().getPosition().getY() > cible.getY()) {
					if ((this.getRobot().getPosition().getY() - cible.getY() <= Constantes
							.getPorteeChar()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirChar());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				} else if (cible.getY() > this.getRobot().getPosition().getY()) {
					if ((cible.getY() - this.getRobot().getPosition().getY() <= Constantes
							.getPorteeChar()) && tireSansEntrave()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutTirChar());
						if (this.cible.getRobot() instanceof Tireur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsTireur());
						} else if (this.cible.getRobot() instanceof Piegeur) {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsPiegeur());
						} else {
							this.cible.getRobot().setEnergie(
									this.cible.getRobot().getEnergie()
											- Constantes.getDegatsChar());
						}
					}
				}
			}
		} else {
			if (this.getRobot().getNbMines() > 0) {
				if (this.getRobot().getPosition().getX() >= cible.getX()
						&& this.getRobot().getPosition().getY() >= cible.getY()) {
					if (this.getRobot().getPosition().getX() - cible.getX() <= Constantes
							.getPorteePiegeur()
							&& this.getRobot().getPosition().getY()
									- cible.getY() <= Constantes
										.getPorteePiegeur()
							&& !cible.estBase()
							&& !cible.estObstacle()
							&& !cible.estRobot()
							&& !cible.estMine()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutMine());
						Position.getPlateau().getCarte()
								.get(Position.getPlateau().posToString(cible))
								.flipMine(this.getRobot().getEquipe());
						this.getRobot().setNbMines(
								this.getRobot().getNbMines() - 1);
					}
				} else if (this.getRobot().getPosition().getX() >= cible.getX()
						&& this.getRobot().getPosition().getY() <= cible.getY()) {
					if (this.getRobot().getPosition().getX() - cible.getX() <= Constantes
							.getPorteePiegeur()
							&& cible.getY()
									- this.getRobot().getPosition().getY() <= Constantes
										.getPorteeTireur()
							&& !cible.estBase()
							&& !cible.estObstacle()
							&& !cible.estRobot()
							&& !cible.estMine()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutMine());
						Position.getPlateau().getCarte()
								.get(Position.getPlateau().posToString(cible))
								.flipMine(this.getRobot().getEquipe());
						this.getRobot().setNbMines(
								this.getRobot().getNbMines() - 1);
					}
				} else if (this.getRobot().getPosition().getX() <= cible.getX()
						&& this.getRobot().getPosition().getY() >= cible.getY()) {
					if (cible.getX() - this.getRobot().getPosition().getX() <= Constantes
							.getPorteePiegeur()
							&& this.getRobot().getPosition().getY()
									- cible.getY() <= Constantes
										.getPorteePiegeur()
							&& !cible.estBase()
							&& !cible.estObstacle()
							&& !cible.estRobot()
							&& !cible.estMine()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutMine());
						Position.getPlateau().getCarte()
								.get(Position.getPlateau().posToString(cible))
								.flipMine(this.getRobot().getEquipe());
						this.getRobot().setNbMines(
								this.getRobot().getNbMines() - 1);
					}
				} else {
					if (cible.getX() - this.getRobot().getPosition().getX() <= Constantes
							.getPorteePiegeur()
							&& cible.getY()
									- this.getRobot().getPosition().getY() <= Constantes
										.getPorteePiegeur()
							&& !cible.estBase()
							&& !cible.estObstacle()
							&& !cible.estRobot()
							&& !cible.estMine()) {
						this.cible = cible;
						this.getRobot().setEnergie(
								this.getRobot().getEnergie()
										- Constantes.getCoutMine());
						Position.getPlateau().getCarte()
								.get(Position.getPlateau().posToString(cible))
								.flipMine(this.getRobot().getEquipe());
						this.getRobot().setNbMines(
								this.getRobot().getNbMines() - 1);
					}
				}
			}
		}
		if(energieInit==getRobot().getEnergie()){
			throw new Erreur("Action impossible");
		}
	}

	/**
	 * verifie si il y a un obstacle sur la trajectoire du tir
	 * 
	 * @return
	 */
	public boolean tireSansEntrave() {
		Position tmp;
		if (cible.getX() == this.getRobot().getPosition().getX()) {
			if (cible.getY() < this.getRobot().getPosition().getY()) {
				for (int v = this.getRobot().getPosition().getY() + 1; v < cible
						.getY(); v++) {
					tmp = new Position(this.getRobot().getPosition().getX(), v);
					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			} else {
				for (int v = this.getRobot().getPosition().getY() - 1; v > cible
						.getY(); v--) {
					tmp = new Position(this.getRobot().getPosition().getX(), v);
					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			}
		} else if (cible.getY() == this.getRobot().getPosition().getY()) {
			if (cible.getX() < this.getRobot().getPosition().getX()) {
				for (int v = this.getRobot().getPosition().getX() + 1; v < cible
						.getX(); v++) {
					tmp = new Position(v, this.getRobot().getPosition().getX());
					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			} else {
				for (int v = this.getRobot().getPosition().getX() - 1; v > cible
						.getX(); v--) {
					tmp = new Position(v, this.getRobot().getPosition().getX());
					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			}
		}
		if (cible.getX() != this.getRobot().getPosition().getX()
				&& cible.getY() == this.getRobot().getPosition().getY()) {
			return false;
		}
		return true;
	}

	public Position getCible() {
		return this.cible;
	}

	public void setCible(Position cible2) {
	} // si la cible initiale ne convient pas

}
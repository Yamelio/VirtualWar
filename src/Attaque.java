public class Attaque extends Action {

	public Attaque(Robot robot, Position cible) throws Erreur {
		super(robot, cible);
		this.cible = Position.getPlateau().getCarte()
				.get(Position.getPlateau().posToString(cible));
		if (getRobot() instanceof Piegeur) {
			if (this.cible.estMine()) {
				throw new Erreur("Cible occupee");
			}
			if (this.cible.estObstacle()) {
				throw new Erreur("Cible occupee");
			}
		}
		int energieInit = getRobot().getEnergie();
		if (robot.getPosition().estBase()) {
			throw new Erreur("Le robot ne peut pas attaquer depuis sa base");
		} else {
			if (this.getRobot() instanceof Tireur) {
				if (this.getRobot().getPosition().getY() == cible.getY()) {
					if (this.getRobot().getPosition().getX() > cible.getX()) {
						if ((this.getRobot().getPosition().getX()
								- cible.getX() <= Constantes.getPorteeTireur())
								&& tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirTireur());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					} else if (cible.getX() > this.getRobot().getPosition()
							.getX()) {
						if ((cible.getX()
								- this.getRobot().getPosition().getX() <= Constantes
									.getPorteeTireur()) && tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirTireur());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					}
				} else if (this.getRobot().getPosition().getX() == cible.getX()) {
					if (this.getRobot().getPosition().getY() > cible.getY()) {
						if ((this.getRobot().getPosition().getY()
								- cible.getY() <= Constantes.getPorteeTireur())
								&& tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirTireur());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					} else if (cible.getY() > this.getRobot().getPosition()
							.getY()) {
						if ((cible.getY()
								- this.getRobot().getPosition().getY() <= Constantes
									.getPorteeTireur()) && tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirTireur());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
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
						if ((this.getRobot().getPosition().getX()
								- cible.getX() <= Constantes.getPorteeChar())
								&& tireSansEntrave()) {
							this.cible = cible;
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirChar());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					} else if (cible.getX() > this.getRobot().getPosition()
							.getX()) {
						if ((cible.getX()
								- this.getRobot().getPosition().getX() <= Constantes
									.getPorteeChar()) && tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirChar());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					}
				} else if (this.getRobot().getPosition().getX() == cible.getX()) {
					if (this.getRobot().getPosition().getY() > cible.getY()) {
						if ((this.getRobot().getPosition().getY()
								- cible.getY() <= Constantes.getPorteeChar())
								&& tireSansEntrave()) {
							this.cible = cible;
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirChar());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
							} else {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsChar());
							}
						}
					} else if (cible.getY() > this.getRobot().getPosition()
							.getY()) {
						if ((cible.getY()
								- this.getRobot().getPosition().getY() <= Constantes
									.getPorteeChar()) && tireSansEntrave()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutTirChar());
							if (this.cible.getRobot() instanceof Tireur) {
								this.cible.getRobot().setEnergie(
										this.cible.getRobot().getEnergie()
												- Constantes.getDegatsTireur());
							} else if (this.cible.getRobot() instanceof Piegeur) {
								this.cible
										.getRobot()
										.setEnergie(
												this.cible.getRobot()
														.getEnergie()
														- Constantes
																.getDegatsPiegeur());
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
							&& this.getRobot().getPosition().getY() >= cible
									.getY()) {
						if (this.getRobot().getPosition().getX() - cible.getX() <= Constantes
								.getPorteePiegeur()
								&& this.getRobot().getPosition().getY()
										- cible.getY() <= Constantes
											.getPorteePiegeur()
								&& !cible.estBase()
								&& !cible.estObstacle()
								&& !cible.estRobot() && !cible.estMine()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutMine());
							Position.getPlateau()
									.getCarte()
									.get(Position.getPlateau().posToString(
											cible))
									.flipMine(this.getRobot().getEquipe());
							this.getRobot().setNbMines(
									this.getRobot().getNbMines() - 1);
						}
					} else if (this.getRobot().getPosition().getX() >= cible
							.getX()
							&& this.getRobot().getPosition().getY() <= cible
									.getY()) {
						if (this.getRobot().getPosition().getX() - cible.getX() <= Constantes
								.getPorteePiegeur()
								&& cible.getY()
										- this.getRobot().getPosition().getY() <= Constantes
											.getPorteeTireur()
								&& !cible.estBase()
								&& !cible.estObstacle()
								&& !cible.estRobot() && !cible.estMine()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutMine());
							Position.getPlateau()
									.getCarte()
									.get(Position.getPlateau().posToString(
											cible))
									.flipMine(this.getRobot().getEquipe());
							this.getRobot().setNbMines(
									this.getRobot().getNbMines() - 1);
						}
					} else if (this.getRobot().getPosition().getX() <= cible
							.getX()
							&& this.getRobot().getPosition().getY() >= cible
									.getY()) {
						if (cible.getX() - this.getRobot().getPosition().getX() <= Constantes
								.getPorteePiegeur()
								&& this.getRobot().getPosition().getY()
										- cible.getY() <= Constantes
											.getPorteePiegeur()
								&& !cible.estBase()
								&& !cible.estObstacle()
								&& !cible.estRobot() && !cible.estMine()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutMine());
							Position.getPlateau()
									.getCarte()
									.get(Position.getPlateau().posToString(
											cible))
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
								&& !cible.estRobot() && !cible.estMine()) {
							this.getRobot().setEnergie(
									this.getRobot().getEnergie()
											- Constantes.getCoutMine());
							Position.getPlateau()
									.getCarte()
									.get(Position.getPlateau().posToString(
											cible))
									.flipMine(this.getRobot().getEquipe());
							this.getRobot().setNbMines(
									this.getRobot().getNbMines() - 1);
						}
					}
				}
			}
			if (energieInit == getRobot().getEnergie()) {
				throw new Erreur("Action impossible");
			}
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
				for (int v = this.getRobot().getPosition().getY() - 1; v > cible
						.getY(); v--) {
					tmp = new Position(this.getRobot().getPosition().getX(), v);
					tmp = Position
							.getPlateau()
							.getCarte()
							.get(Position.getPlateau().posToString(
									new Position(tmp.getX(), tmp.getY())));

					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			} else {
				for (int v = this.getRobot().getPosition().getY() + 1; v < cible
						.getY(); v++) {
					tmp = new Position(this.getRobot().getPosition().getX(), v);
					tmp = Position
							.getPlateau()
							.getCarte()
							.get(Position.getPlateau().posToString(
									new Position(tmp.getX(), tmp.getY())));

					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			}
		} else if (cible.getY() == this.getRobot().getPosition().getY()) {
			if (cible.getX() < this.getRobot().getPosition().getX()) {
				for (int v = this.getRobot().getPosition().getX() - 1; v > cible
						.getX(); v--) {
					tmp = new Position(v, this.getRobot().getPosition().getY());
					tmp = Position
							.getPlateau()
							.getCarte()
							.get(Position.getPlateau().posToString(
									new Position(tmp.getX(), tmp.getY())));

					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			} else {
				for (int v = this.getRobot().getPosition().getX() + 1; v < cible
						.getX(); v++) {
					tmp = new Position(v, this.getRobot().getPosition().getY());
					tmp = Position
							.getPlateau()
							.getCarte()
							.get(Position.getPlateau().posToString(
									new Position(tmp.getX(), tmp.getY())));

					if (tmp.estRobot() || tmp.estBase() || tmp.estObstacle()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Position getCible() {
		return this.cible;
	}

	public String toString() {
		return this.getRobot().getEquipe() + " " + this.getRobot().getId()
				+ " " + 1 + " " + Position.getPlateau().posToString(cible);
	}

}
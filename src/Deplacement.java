/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe represent une
 *         Action Deplacement definit a partir de sa cible
 */

public class Deplacement extends Action {
	/**
	 * 
	 * Constructeur de la classe Deplacement
	 * 
	 * @param robot
	 *            Robot qui se deplace
	 * @param cible
	 *            Cible vers laquelle il se deplace
	 * @throws Erreur
	 *             Erreur en cas de cible incorrecte
	 */
	public Deplacement(Robot robot, Position cible) throws Erreur {
		super(robot, cible);
		this.cible = Position.getPlateau().getCarte()
				.get(Position.getPlateau().posToString(cible));
		if (checkTotalRobots()) {
			if (checkCoordonees()) {
				if (checkObstacle()) {
					if (!checkBase()) {
						if (this.cible.estBase()) {
							if (this.cible.getEquipe() != getRobot()
									.getEquipe()) {
								throw new Erreur("Ce n'est pas votre base");
							}
						} else {
							getRobot().setPosition(cible);

						}
					}
					deplacerRobot();
					checkMine(this.cible);
					this.cible = Position.getPlateau().getCarte()
							.get(Position.getPlateau().posToString(cible));

				} else {
					throw new Erreur("Obstacle sur la case");
				}
			} else {
				throw new Erreur("Case non atteignable");
			}
		} else {
			throw new Erreur("Au moins un robot hors de la base");
		}

	}

	/**
	 * Retourne la cible du deplacement
	 * 
	 * @return la cible du deplacement
	 */
	public Position getCible() {
		return this.cible;
	}

	/**
	 * Fonction qui verifie si la cible est une base ou si le robot est dans sa
	 * base et le fait rentrer ou sortir
	 */
	public boolean checkBase() {
		if (getRobot().getPosition().estBase()) {
			((Base) getRobot().getPosition()).quitteBase(getRobot());
			getRobot().setPosition(cible);
			return true;
		} else if (cible.estBase()
				&& cible.getEquipe() == getRobot().getEquipe()) {
			((Base) cible).deplaceSur(getRobot());
			getRobot().setPosition(cible);
			return true;
		}

		return false;
	}

	/**
	 * Fonction qui verifie si un robot est au moins en dehors de sa base
	 * 
	 * @return vrai si oui, faux si non
	 */
	private boolean checkTotalRobots() {
		if (this.cible.estBase()) {
			int equipe = this.getRobot().getEquipe();
			int cpt = 0;

			for (Robot r : Position.getPlateau().getListeRobot()) {
				if (r.getEquipe() == equipe && !r.getPosition().estBase()) {
					cpt++;
				}
			}
			// if (cpt == 0) {
			// return true;
			// }
			return cpt > 1;
		}
		return true;
	}

	/**
	 * Fonction qui verifie que la cible du deplacement est bien a portee du
	 * robot
	 */
	public boolean checkCoordonees() {
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
				return true;
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
							.getRobot().getPosition().getX() == cible.getX()))
				if (charPeutDeplacer()) {
					return true;
				}
		}
		return false;
	}

	/**
	 * Fonction qui verifie si la cible est une mine et enleve l'energie en
	 * consequant
	 * 
	 * @return vrai si oui, faux si non
	 */
	public boolean checkMine(Position p) {
		if (!p.estMine()) {
			return false;
		} else {
			getRobot().setEnergie(
					getRobot().getEnergie() - Constantes.getDegatsPiegeur());
			Position.getPlateau().getCarte()
					.get(Position.getPlateau().posToString(p)).flipMine(2);
			return true;
		}
	}

	/**
	 * Fonction qui verifie si la cible est un occupee
	 * 
	 * @return vrai si non, faux si oui
	 */
	public boolean checkObstacle() {
		return !(cible.estRobot() || cible.estObstacle());
	}

	/**
	 * Fonction qui deplace le robot
	 */
	public void deplacerRobot() {

		if (this.getRobot() instanceof Tireur) {
			this.getRobot().setEnergie(
					this.getRobot().getEnergie()
							- Constantes.getCoutDeplacementTireur());
		} else if (this.getRobot() instanceof Piegeur) {
			this.getRobot().setEnergie(
					this.getRobot().getEnergie()
							- Constantes.getCoutDeplacementPiegeur());
		} else {
			this.getRobot().setEnergie(
					this.getRobot().getEnergie()
							- Constantes.getCoutDeplacementChar());
		}
		this.getRobot().setPosition(this.cible);
	}

	public String toString() {
		return this.getRobot().getEquipe() + " " + this.getRobot().getId()
				+ " " + 0 + " " + Position.getPlateau().posToString(cible);
	}

	/**
	 * Fonction qui verifie si un char n'as pas d'obstacle le long du trajet
	 * 
	 * @return vrai si oui, faux si non
	 */
	public boolean charPeutDeplacer() {
		boolean chgtCible = cible.estObstacle() || cible.estRobot();
		Position tmp = null;
		if (cible.getX() == this.getRobot().getPosition().getX()) {
			if (cible.getY() < this.getRobot().getPosition().getY()) {
				tmp = Position
						.getPlateau()
						.getCarte()
						.get(Position.getPlateau().posToString(
								new Position(cible.getX(), cible.getY() + 1)));
			} else {
				tmp = Position
						.getPlateau()
						.getCarte()
						.get(Position.getPlateau().posToString(
								new Position(cible.getX(), cible.getY() - 1)));
			}
		} else if (cible.getY() == this.getRobot().getPosition().getY()) {
			if (cible.getX() < this.getRobot().getPosition().getX()) {
				tmp = Position
						.getPlateau()
						.getCarte()
						.get(Position.getPlateau().posToString(
								new Position(cible.getX() + 1, cible.getY())));
			} else {
				tmp = Position
						.getPlateau()
						.getCarte()
						.get(Position.getPlateau().posToString(
								new Position(cible.getX() - 1, cible.getY())));

			}
		}
		if (tmp != null) {
			if (chgtCible) {
				this.cible = tmp;
			}
			checkMine(tmp);
			return checkObstacle();
		} else {
			return false;
		}
	}
}

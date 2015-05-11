/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe représent une
 *         Action Déplacement définit à partir de sa cible
 */

public class Deplacement extends Action {

	/** La cible du déplacement */

	/**
	 * 
	 * Constructeur de la classe Deplacement
	 * 
	 * @param robot
	 *            Robot qui se déplace
	 * @param cible
	 *            Cible vers laquelle il se déplace
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

	public Position getCible() {
		return this.cible;
	}

	/**
	 * Fonction qui vérifie si la cible est une base ou si le robot est dans sa
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
	 * Fonction qui vérifie que la cible du déplacement est bien à portée du
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
	 * Fonction qui vérifie si la cible est une mine et enlève l'énergie en
	 * conséquant
	 * 
	 * @return
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

	public boolean checkObstacle() {
		return !(cible.estRobot() || cible.estObstacle());
	}

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

	}

	public String toString() {
		return this.getRobot().getEquipe() + " " + this.getRobot().getId()
				+ " " + 0 + " " + Position.getPlateau().posToString(cible);
	}

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
			return !(tmp.estObstacle() || tmp.estRobot());
		} else {
			return false;
		}
	}
}

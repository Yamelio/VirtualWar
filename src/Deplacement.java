/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe repr�sent une
 *         Action D�placement d�finit � partir de sa cible
 */

public class Deplacement extends Action {

	/** La cible du d�placement */
	private Position cible;

	/**
	 * 
	 * Constructeur de la classe Deplacement
	 * 
	 * @param robot
	 *            Robot qui se d�place
	 * @param cible
	 *            Cible vers laquelle il se d�place
	 * @throws Erreur
	 *             Erreur en cas de cible incorrecte
	 */
	public Deplacement(Robot robot, Position cible) throws Erreur {
		super(robot);
		this.cible = Position.getPlateau().getCarte()
				.get(Position.getPlateau().posToString(cible));

		if (checkCoordonees()) {
			if (checkObstacle()) {
				deplacerRobot();
				checkMine();
			} else {
				throw new Erreur("Obstacle sur la case");
			}
		} else {
			throw new Erreur("Case non atteignable");
		}

	}

	public Position getCible() {
		return this.cible;
	}

	/**
	 * Fonction qui v�rifie si la cible est une base ou si le robot est dans sa
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
	 * Fonction qui v�rifie que la cible du d�placement est bien � port�e du
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
				return true;
		}
		return false;
	}

	/**
	 * Fonction qui v�rifie si la cible est une mine et enl�ve l'�nergie en
	 * cons�quant
	 * 
	 * @return
	 */
	public boolean checkMine() {
		if (!cible.estMine()) {
			return false;
		} else {
			getRobot().setEnergie(
					getRobot().getEnergie() - Constantes.getDegatsPiegeur());
			Position.getPlateau().getCarte()
					.get(Position.getPlateau().posToString(cible)).flipMine(2);
			return true;
		}
	}

	public boolean checkObstacle() {
		return !(cible.estRobot() || cible.estObstacle());
	}

	public void deplacerRobot() {

		if (!checkBase()) {
			getRobot().setPosition(cible);
		}

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
}

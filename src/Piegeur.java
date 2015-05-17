/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe repr�sente un Robot
 *         Piegeur d�finit par son �quipe
 */

public class Piegeur extends Robot {

	/** Le nombre de mines restant en stock */
	private int nbMines = Constantes.getMinesInit();

	/**
	 * 
	 * Constructeur de la classe Piegeur
	 * 
	 * @param equipe
	 *            l'�quipe du Piegeur
	 */
	public Piegeur(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialePiegeur());
	}

	/**
	 * Fonction qui recharge en plus de la vie, le stock de mines
	 */
	public void recharger() {
		super.recharger();
		this.nbMines = Constantes.getMinesInit();
	}

	public int getNbMines() {
		return this.nbMines;
	}

	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}

	public String toString() {
		return "P" + getId();
	}
}

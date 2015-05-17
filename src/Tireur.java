/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe represente un Robot
 *         Tireur, definit a partir de son equipe
 */
public class Tireur extends Robot {

	/**
	 * Constructeur de la classe Tireur
	 * 
	 * @param equipe
	 *            Son equipe
	 */
	public Tireur(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialeTireur());
	}

	public int getNbMines() {
		return 0;
	}

	@Override
	public void setNbMines(int nbMines) {
	}

	public String toString() {
		return "T" + getId();
	}

}

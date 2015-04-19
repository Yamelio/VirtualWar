/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un Robot Tireur, définit à partir de son équipe
 */
public class Tireur extends Robot {

	/**
	 * Constructeur de la classe Tireur
	 * 
	 * @param equipe
	 * 					Son équipe
	 */
	public Tireur(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialeTireur());
	}

	@Override
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

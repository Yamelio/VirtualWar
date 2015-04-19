/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un Robot Tireur, d�finit � partir de son �quipe
 */
public class Tireur extends Robot {

	/**
	 * Constructeur de la classe Tireur
	 * 
	 * @param equipe
	 * 					Son �quipe
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
